package encryption.algorithms;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by yurisho on 09/08/2016.
 *
 * test encryption.algorithms.DoubleAlgorithmDecorator class
 */
public class DoubleAlgorithmDecoratorTest {
    private File original;
    private File encrypted;
    private File decrypted;

    private XorEncryptionAlgorithmDecorator encryptionAlgorithm1;
    private MultiplicationEncryptionAlgorithmDecorator encryptionAlgorithm2;

    private XorDecryptionAlgorithmDecorator decryptionAlgorithm1;
    private MultiplicationDecryptionAlgorithmDecorator decryptionAlgorithm2;

    private DoubleAlgorithmDecorator doubleAlgorithmDecorator;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        final String fileName = "text.txt";
        String testFilePath;

        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        folder.newFile(fileName);
        original = new File(testFilePath);
        encrypted = new File(testFilePath + ".encrypted");

        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        String testFilePathWOExtension = testFilePath.substring(0, testFilePath.lastIndexOf('.'));
        String testFilePathExtension = testFilePath.substring(testFilePath.lastIndexOf("."));
        decrypted = new File(testFilePathWOExtension + "_decrypted" + testFilePathExtension);

        encryptionAlgorithm1 = new XorEncryptionAlgorithmDecorator(new BasicAlgorithm(), (char)10);
        encryptionAlgorithm2 = new MultiplicationEncryptionAlgorithmDecorator(new BasicAlgorithm(), (char)7);

        decryptionAlgorithm1 = new XorDecryptionAlgorithmDecorator(new BasicAlgorithm(), (char)10);
        decryptionAlgorithm2 = new MultiplicationDecryptionAlgorithmDecorator(new BasicAlgorithm(), (char)7);
    }

    @After
    public void tearDown() throws Exception {
        doubleAlgorithmDecorator = null;
        encryptionAlgorithm2 = null;

        decryptionAlgorithm1 = null;
        decryptionAlgorithm2 = null;

        encryptionAlgorithm1 = null;
    }

    @Test
    public void algorithmShouldXorThenMultiplicationEncryptAndMultiplicationThenXorDecryptTheFile() throws Exception {
        String fileContent = "Hello, world!";

        //write test data to file
        PrintWriter writer = new PrintWriter(original, "UTF-8");
        writer.println(fileContent);
        writer.close();

        doubleAlgorithmDecorator = new DoubleAlgorithmDecorator(encryptionAlgorithm1, encryptionAlgorithm2);
        doubleAlgorithmDecorator.setInputFile(original);
        doubleAlgorithmDecorator.setOutputFile(encrypted);
        doubleAlgorithmDecorator.init();
        doubleAlgorithmDecorator.algorithm();

        doubleAlgorithmDecorator = new DoubleAlgorithmDecorator(decryptionAlgorithm2, decryptionAlgorithm1);
        doubleAlgorithmDecorator.setInputFile(encrypted);
        doubleAlgorithmDecorator.setOutputFile(decrypted);
        doubleAlgorithmDecorator.init();
        doubleAlgorithmDecorator.algorithm();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine(), is(fileContent));
    }

}