package encryption.algorithms;

import encryption.exception.DecryptionKeyNotFoundException;
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
 */
public class MultiplicationDecryptionAlgorithmDecoratorTest {
    private File encrypted;
    private File decrypted;
    private final char key = (char) 7;
    private MultiplicationDecryptionAlgorithmDecorator decryptionAlgorithm;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

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

        decryptionAlgorithm = new MultiplicationDecryptionAlgorithmDecorator(new BasicAlgorithm(), key);

    }

    @After
    public void tearDown() {
        decryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldMultiplicationDecryptTheFile() throws IOException, EncryptionException {
        String fileContentDecrypted = "Hello, world!";

        char[] fileContentMultiplicationEncryptedByteArray = {0xf8, 0xc3, 0xf4, 0xf4, 0x09, 0x34, 0xe0, 0x41, 0x09, 0x1e, 0xf4, 0xbc, 0xe7};
        String fileContentMultiplicationEncrypted = new String(fileContentMultiplicationEncryptedByteArray);

        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentMultiplicationEncrypted);
        writer.close();

        decryptionAlgorithm.setInputFile(encrypted);
        decryptionAlgorithm.setOutputFile(decrypted);
        decryptionAlgorithm.init();
        decryptionAlgorithm.algorithm();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine().substring(0, 13), is(fileContentDecrypted)); // use substring to remove extra bytes at end of file
    }

    @Test
    public void FindDecryptionKeyShouldReturnTheRightKey() {
        try {
            assertThat(MultiplicationDecryptionAlgorithmDecorator.FindDecryptionKey(decryptionAlgorithm.getStep(1).getValue()), is((char) 183));
        } catch (DecryptionKeyNotFoundException exp) {
            fail();
        }
    }

    @Test
    public void algorithmWithEvenKeyShouldThrowIllegalKeyException() throws IOException, EncryptionException {
        exceptionGrabber.expect(IllegalKeyException.class);

        decryptionAlgorithm = new MultiplicationDecryptionAlgorithmDecorator(new BasicAlgorithm(), (char)6);

        decryptionAlgorithm.setInputFile(encrypted);
        decryptionAlgorithm.setOutputFile(decrypted);
        decryptionAlgorithm.init();
        decryptionAlgorithm.algorithm();
    }

    @Test
    public void algorithmWithZeroKeyShouldThrowIllegalKeyException() throws IOException, EncryptionException {
        exceptionGrabber.expect(IllegalKeyException.class);

        decryptionAlgorithm = new MultiplicationDecryptionAlgorithmDecorator(new BasicAlgorithm(), (char)0);

        decryptionAlgorithm.setInputFile(encrypted);
        decryptionAlgorithm.setOutputFile(decrypted);
        decryptionAlgorithm.init();
        decryptionAlgorithm.algorithm();
    }
}