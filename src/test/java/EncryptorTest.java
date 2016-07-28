import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 21/07/2016.
 */
public class EncryptorTest {
    private final String fileName = "text.txt";
    private final String fileContent = "Hello, world!";
    private final byte key = 10;
    private final String fileContentCaesarEncrypted = "Rovvy, gybvn!"; // encryption checked with http://www.xarg.org/tools/caesar-cipher/

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private String testFilePath;
    private File original;
    private File encrypted;

    private Encryptor encryptor;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpEncryptor() throws IOException {
        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        folder.newFile(fileName);
        original = new File(testFilePath);
        encrypted = new File(testFilePath + ".encrypted");

        //write test data to file
        PrintWriter writer = new PrintWriter(testFilePath, "UTF-8");
        writer.println(fileContent);
        writer.close();

        encryptor = new Encryptor(testFilePath, key, AlgorithmTypeEnum.NONE);
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDownEncryptor() {
        encryptor = null;
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void preformFunctionShouldWriteToScreen() {
        encryptor.run();
        assertThat(outContent.toString(), is("encryption simulation of file " + encryptor.getFilePath() + "\r\n"));
    }

    @Test
    public void EncryptNoneShouldNotChangeTheFileContent() throws IOException {
        encryptor.setAlgorithmType(AlgorithmTypeEnum.NONE);

        encryptor.run();

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine(), is(fileContent));
    }

    @Test
    public void EncryptCaesarShouldEncryptTheFileContent() throws IOException {
        encryptor.setAlgorithmType(AlgorithmTypeEnum.CAESAR);

        encryptor.run();

        BufferedReader encryptedReader = new BufferedReader(new FileReader(encrypted));

        assertThat(encryptedReader.readLine(), is(fileContentCaesarEncrypted));
    }

}