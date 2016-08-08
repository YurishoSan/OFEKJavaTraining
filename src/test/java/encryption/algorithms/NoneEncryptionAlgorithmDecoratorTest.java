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
 * Test encryption.algorithms.NoneEncryptionAlgorithmDecorator class
 */
public class NoneEncryptionAlgorithmDecoratorTest {
    private File original;
    private File encrypted;

    private final List<Character> keys = Collections.singletonList((char) 10);

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

        encryptionAlgorithm = new NoneEncryptionAlgorithmDecorator(new BasicAlgorithm(), (char)0);
    }

    @After
    public void tearDown() {
        encryptionAlgorithm = null;
    }

    @Test
    public void algorithmShouldWriteTheFileAsIs() throws IOException, EncryptionException {
        String fileContent = "Hello, world!";

        //write test data to file
        PrintWriter writer = new PrintWriter(original, "UTF-8");
        writer.println(fileContent);
        writer.close();

        encryptionAlgorithm.setInputFile(original);
        encryptionAlgorithm.setOutputFile(encrypted);
        encryptionAlgorithm.init();
        encryptionAlgorithm.algorithm();

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine(), is(fileContent));
    }
}