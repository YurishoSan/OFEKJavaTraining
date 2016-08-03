import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 02/08/2016.
 */
public class XorDecryptionAlgorithmDecoratorTest {
    private File encrypted;
    private File decrypted;
    private final char key = 10;
    private XorDecryptionAlgorithmDecorator decryptionAlgorithm;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        String testFilePath;
        final String fileName = "text.txt";

        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        String testFilePathWOExtension = testFilePath.substring(0, testFilePath.lastIndexOf('.'));
        String testFilePathExtension = testFilePath.substring(testFilePath.lastIndexOf("."));
        folder.newFile(fileName + ".encrypted");
        encrypted = new File(testFilePath + ".encrypted");
        decrypted = new File(testFilePathWOExtension + "_decrypted" + testFilePathExtension);

        decryptionAlgorithm = new XorDecryptionAlgorithmDecorator( new EncryptionAlgorithm() {
            public void algorithm(FileReader inputFile, FileWriter outputFile, char key) throws IOException {

            }
        });

    }

    @After
    public void tearDown() {
        decryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldCaesarDecryptTheFile() throws IOException, IllegalKeyException {
        String fileContentDecrypted = "Hello, world!";

        char[] fileContentXorEncryptedByteArray = {0x42,0x6f,0x66,0x66,0x65,0x26,0x2a,0x7d,0x65,0x78,0x66,0x6e,0x2b};
        String fileContentXorEncrypted = new String(fileContentXorEncryptedByteArray);

        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentXorEncrypted);
        writer.close();

        decryptionAlgorithm.algorithm(new FileReader(encrypted), new FileWriter(decrypted), key);

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine().substring(0,13), is(fileContentDecrypted)); // use substring to remove extra bytes at end of file
    }

}