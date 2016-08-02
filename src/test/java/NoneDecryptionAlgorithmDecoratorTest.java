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
 *
 * Test NoneDecryptionAlgorithmDecorator class
 */
public class NoneDecryptionAlgorithmDecoratorTest {
    private final String fileContentDecrypted = "Hello, world!";

    private File encrypted;
    private File decrypted;
    private final byte key = 10;
    private NoneDecryptionAlgorithmDecorator decryptionAlgorithm;

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

        decryptionAlgorithm = new NoneDecryptionAlgorithmDecorator( new EncryptionAlgorithm() {
            public void algorithm(FileInputStream inputFile, FileOutputStream outputFile, byte key) throws IOException {

            }
        });

    }

    @After
    public void tearDown() {
        decryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldWriteTheFileAsIs() throws IOException {
        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentDecrypted);
        writer.close();

        decryptionAlgorithm.algorithm(new FileInputStream(encrypted), new FileOutputStream(decrypted), key);

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine(), is(fileContentDecrypted));
    }

}