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
public class MultiplicationDecryptionAlgorithmDecoratorTest {
    private File encrypted;
    private File decrypted;
    private final char key = 7;
    private MultiplicationDecryptionAlgorithmDecorator decryptionAlgorithm;

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

        decryptionAlgorithm = new MultiplicationDecryptionAlgorithmDecorator( new EncryptionAlgorithm() {
            public void algorithm(FileReader inputFile, FileWriter outputFile, char key) throws IOException {

            }
        });

    }

    @After
    public void tearDown() {
        decryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldCaesarDecryptTheFile() throws IOException {
        String fileContentDecrypted = "Hello, world!";

        char[] fileContentMultiplicationEncryptedByteArray = {0xd0,0xf2,0x38,0x38,0x56,0xb8,0x40,0xa6,0x56,0x74,0x38,0xe8,0x4a};
        String fileContentMultiplicationEncrypted= new String(fileContentMultiplicationEncryptedByteArray);

        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentMultiplicationEncrypted);
        writer.close();

        decryptionAlgorithm.algorithm(new FileReader(encrypted), new FileWriter(decrypted), key);

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine().substring(0,13), is(fileContentDecrypted)); // use substring to remove extra bytes at end of file
    }

    @Test
    public void FindDecryptionKeyShouldReturnTheRightKey() {
        assertThat(MultiplicationDecryptionAlgorithmDecorator.FindDecryptionKey(key), is((char)183));
    }

}