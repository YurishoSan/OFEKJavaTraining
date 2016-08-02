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
 * Test NoneEncryptionAlgorithmDecorator class
 */
public class NoneEncryptionAlgorithmDecoratorTest {
    private final String fileContent = "Hello, world!";

    private File original;
    private File encrypted;

    private final byte key = 10;

    private NoneEncryptionAlgorithmDecorator encryptionAlgorithm;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        final String fileName = "text.txt";
        String testFilePath;

        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        folder.newFile(fileName);
        original = new File(testFilePath);
        encrypted = new File(testFilePath + ".encrypted");

        //write test data to file
        PrintWriter writer = new PrintWriter(original, "UTF-8");
        writer.println(fileContent);
        writer.close();

        encryptionAlgorithm = new NoneEncryptionAlgorithmDecorator( new EncryptionAlgorithm() {
            public void algorithm(FileInputStream inputFile, FileOutputStream outputFile, byte key) throws IOException {

            }
        });
    }

    @After
    public void tearDown() {
        encryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldWriteTheFileAsIs() throws IOException {

        encryptionAlgorithm.algorithm(new FileInputStream(original), new FileOutputStream(encrypted), key);

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine(), is(fileContent));
    }
}