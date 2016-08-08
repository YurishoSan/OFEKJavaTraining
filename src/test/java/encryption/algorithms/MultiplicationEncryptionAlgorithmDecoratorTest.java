package encryption.algorithms;

import encryption.exception.EncryptionException;
import encryption.exception.IllegalKeyException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Test encryption.algorithms.MultiplicationEncryptionAlgorithmDecorator class
 */
public class MultiplicationEncryptionAlgorithmDecoratorTest {
    private File original;
    private File encrypted;

    private final char key = (char)7;

    private MultiplicationEncryptionAlgorithmDecorator encryptionAlgorithm;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Before
    public void setUp() throws IOException {
        final String fileName = "text.txt";
        String testFilePath;

        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        folder.newFile(fileName);
        original = new File(testFilePath);
        encrypted = new File(testFilePath + ".encrypted");

        encryptionAlgorithm = new MultiplicationEncryptionAlgorithmDecorator(new BasicAlgorithm(), key);
    }

    @After
    public void tearDown() {
        encryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldMultiplicationEncryptTheFile() throws IOException, EncryptionException {
        String fileContent = "Hello, world!";

        //write test data to file
        PrintWriter writer = new PrintWriter(original, "UTF-8");
        writer.println(fileContent);
        writer.close();

        char[] fileContentMultiplicationEncryptedByteArray = {0xf8,0xc3,0xf4,0xf4,0x09,0x34,0xe0,0x41,0x09,0x1e,0xf4,0xbc,0xe7};
        String fileContentMultiplicationEncrypted = new String(fileContentMultiplicationEncryptedByteArray);

        encryptionAlgorithm.setInputFile(original);
        encryptionAlgorithm.setOutputFile(encrypted);
        encryptionAlgorithm.init();
        encryptionAlgorithm.algorithm();

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine().substring(0,13), is(fileContentMultiplicationEncrypted)); // use substring to remove extra bytes at end of file
    }

    @Test
    public void algorithmWithEvenKeyShouldThrowIllegalKeyException() throws IOException, EncryptionException {
        exceptionGrabber.expect(IllegalKeyException.class);

        encryptionAlgorithm = new MultiplicationEncryptionAlgorithmDecorator(new BasicAlgorithm(), (char)6);

        encryptionAlgorithm.setInputFile(original);
        encryptionAlgorithm.setOutputFile(encrypted);
        encryptionAlgorithm.init();
        encryptionAlgorithm.algorithm();
    }

    @Test
    public void algorithmWithZeroKeyShouldThrowIllegalKeyException() throws IOException, EncryptionException {
        exceptionGrabber.expect(IllegalKeyException.class);

        encryptionAlgorithm = new MultiplicationEncryptionAlgorithmDecorator(new BasicAlgorithm(), (char)0);

        encryptionAlgorithm.setInputFile(original);
        encryptionAlgorithm.setOutputFile(encrypted);
        encryptionAlgorithm.init();
        encryptionAlgorithm.algorithm();
    }
}