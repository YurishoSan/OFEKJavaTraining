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
 * Test encryption.algorithms.XorEncryptionAlgorithmDecorator class
 */
public class XorEncryptionAlgorithmDecoratorTest {
    private File original;
    private File encrypted;

    private final char key =(char)10;

    private XorEncryptionAlgorithmDecorator encryptionAlgorithm;

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

        encryptionAlgorithm = new XorEncryptionAlgorithmDecorator(new BasicAlgorithm(), key);
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

        char[] fileContentXorEncryptedByteArray = {0x42,0x6f,0x66,0x66,0x65,0x26,0x2a,0x7d,0x65,0x78,0x66,0x6e,0x2b};
        String fileContentXorEncrypted = new String(fileContentXorEncryptedByteArray);

        encryptionAlgorithm.setInputFile(original);
        encryptionAlgorithm.setOutputFile(encrypted);
        encryptionAlgorithm.init();
        encryptionAlgorithm.algorithm();

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine().substring(0,13), is(fileContentXorEncrypted)); // use substring to remove extra bytes at end of file
    }
}