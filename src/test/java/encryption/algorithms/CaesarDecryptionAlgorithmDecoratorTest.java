package encryption.algorithms;

import encryption.exception.EncryptionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 01/08/2016.
 *
 * Test encryption.algorithms.CaesarDecryptionAlgorithmDecorator class
 */
public class CaesarDecryptionAlgorithmDecoratorTest {

    private File encrypted;
    private File decrypted;
    private final char key = (char)10;

    private CaesarDecryptionAlgorithmDecorator decryptionAlgorithm;

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

        decryptionAlgorithm = new CaesarDecryptionAlgorithmDecorator(new BasicAlgorithm(), key);

    }

    @After
    public void tearDown() {
        decryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldCaesarDecryptTheFile() throws IOException, EncryptionException {
        String fileContentDecrypted = "Hello, world!";
        char[] fileContentCaesarEncryptedByteArray = {0x52,0x6f,0x76,0x76,0x79,0x36,0x2a,0x81,0x79,0x7c,0x76,0x6e,0x2b};
        String fileContentCaesarEncrypted = new String(fileContentCaesarEncryptedByteArray);

        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentCaesarEncrypted);
        writer.close();

        decryptionAlgorithm.setInputFile(encrypted);
        decryptionAlgorithm.setOutputFile(decrypted);
        decryptionAlgorithm.init();
        decryptionAlgorithm.algorithm();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine().substring(0,13), is(fileContentDecrypted)); // use substring to remove extra bytes at end of file
    }

    @Test
    public void algorithmShouldCaesarDecryptTheFileWithUnderflow() throws IOException, EncryptionException {
        String fileContentDecrypted = "Hello, world!ÿ";
        char[] fileContentCaesarEncryptedByteArray = {0x52,0x6f,0x76,0x76,0x79,0x36,0x2a,0x81,0x79,0x7c,0x76,0x6e,0x2b, 0x09};
        String fileContentCaesarEncrypted = new String(fileContentCaesarEncryptedByteArray);

        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentCaesarEncrypted);
        writer.close();

        decryptionAlgorithm.setInputFile(encrypted);
        decryptionAlgorithm.setOutputFile(decrypted);
        decryptionAlgorithm.init();
        decryptionAlgorithm.algorithm();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine().substring(0,14), is(fileContentDecrypted)); // use substring to remove extra bytes at end of file
    }

}