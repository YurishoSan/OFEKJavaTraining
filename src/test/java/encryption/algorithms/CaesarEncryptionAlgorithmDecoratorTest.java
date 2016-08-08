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
 * Created by yurisho on 02/08/2016.
 *
 * Test encryption.algorithms.CaesarEncryptionAlgorithmDecorator class
 */
public class CaesarEncryptionAlgorithmDecoratorTest {
    private final String fileContent = "Hello, world!";

    private File original;
    private File encrypted;

    private final char key = (char)10;

    private CaesarEncryptionAlgorithmDecorator encryptionAlgorithm;

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

        encryptionAlgorithm = new CaesarEncryptionAlgorithmDecorator(new BasicAlgorithm(), key);
    }

    @After
    public void tearDown() {
        encryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldCaesarEncryptTheFile() throws IOException, EncryptionException {
        String fileContent = "Hello, world!";

        //write test data to file
        PrintWriter writer = new PrintWriter(original, "UTF-8");
        writer.println(fileContent);
        writer.close();

        char[] fileContentCaesarEncryptedByteArray = {0x52,0x6f,0x76,0x76,0x79,0x36,0x2a,0x81,0x79,0x7c,0x76,0x6e,0x2b};
        String fileContentCaesarEncrypted = new String(fileContentCaesarEncryptedByteArray);

        encryptionAlgorithm.setInputFile(original);
        encryptionAlgorithm.setOutputFile(encrypted);;
        encryptionAlgorithm.init();
        encryptionAlgorithm.algorithm();

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine().substring(0,13), is(fileContentCaesarEncrypted)); // use substring to remove extra bytes at end of file
    }

    @Test
    public void algorithmShouldCaesarEncryptTheFileWithOverflow() throws IOException, EncryptionException {
        String fileContent = "Hello, world!ÿ";

        //write test data to file
        PrintWriter writer = new PrintWriter(original, "UTF-8");
        writer.println(fileContent);
        writer.close();

        char[] fileContentCaesarEncryptedByteArray = {0x52,0x6f,0x76,0x76,0x79,0x36,0x2a,0x81,0x79,0x7c,0x76,0x6e,0x2b, 0x09};
        String fileContentCaesarEncrypted = new String(fileContentCaesarEncryptedByteArray);

        encryptionAlgorithm.setInputFile(original);
        encryptionAlgorithm.setOutputFile(encrypted);
        encryptionAlgorithm.init();
        encryptionAlgorithm.algorithm();

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine().substring(0,14), is(fileContentCaesarEncrypted)); // use substring to remove extra bytes at end of file
    }
}