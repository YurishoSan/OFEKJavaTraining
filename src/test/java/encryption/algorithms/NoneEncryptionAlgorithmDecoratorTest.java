package encryption.algorithms;

import encryption.design.decorator.EncryptionAlgorithm;
import encryption.exception.IllegalKeyException;
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
 * Test encryption.algorithms.NoneEncryptionAlgorithmDecorator class
 */
public class NoneEncryptionAlgorithmDecoratorTest {
    private File original;
    private File encrypted;

    private final char key = 10;

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

        encryptionAlgorithm = new NoneEncryptionAlgorithmDecorator(new EncryptionAlgorithm() {
            public void algorithm(FileReader inputFile, FileWriter outputFile, char key) throws IOException {

            }
        });
    }

    @After
    public void tearDown() {
        encryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldWriteTheFileAsIs() throws IOException, IllegalKeyException {
        String fileContent = "Hello, world!";

        //write test data to file
        PrintWriter writer = new PrintWriter(original, "UTF-8");
        writer.println(fileContent);
        writer.close();

        encryptionAlgorithm.algorithm(new FileReader(original), new FileWriter(encrypted), key);

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine(), is(fileContent));
    }
}