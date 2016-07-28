import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 21/07/2016.
 */
public class DecryptorTest {
    private final String fileName = "text.txt";
    private final String fileContentDecrypted = "Hello, world!";
    private final byte key = 10;
    private final String fileContentCaesarEncrypted = "Rovvy, gybvn!"; // encryption checked with http://www.xarg.org/tools/caesar-cipher/

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private String testFilePath;
    private File encrypted;
    private File decrypted;

    private Decryptor decryptor;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpDecryptor() throws IOException {
        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        String testFilePathWOExtension = testFilePath.substring(0, testFilePath.lastIndexOf('.'));
        String testFilePathExtension = testFilePath.substring(testFilePath.lastIndexOf("."));
        folder.newFile(fileName + ".encrypted");
        encrypted = new File(testFilePath + ".encrypted");
        decrypted = new File(testFilePathWOExtension + "_decrypted" + testFilePathExtension);

        decryptor = new Decryptor(folder.getRoot().getCanonicalPath() + "\\test.txt", key, AlgorithmTypeEnum.NONE);
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDownDecryptor() {
        decryptor = null;
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void setFilePathShouldFail() throws IOException {
        folder.newFile("myFile.txt");

        decryptor.setFilePath(folder.getRoot().getCanonicalPath() + "\\myFile.txt"); //no '.encrypted' extension
        assertThat(decryptor.getFilePath(), is(""));
    }

    @Test
    public void setFilePathShouldSucceed() throws IOException {
        folder.newFile("myFile.txt.encrypted");
        folder.newFile("myFile.encrypted");

        decryptor.setFilePath(folder.getRoot().getCanonicalPath() + "\\myFile.txt.encrypted");
        assertThat(decryptor.getFilePath(), is(folder.getRoot().getCanonicalPath() + "\\myFile.txt.encrypted"));

        decryptor.setFilePath(folder.getRoot().getCanonicalPath() + "\\myFile.encrypted");
        assertThat(decryptor.getFilePath(), is(folder.getRoot().getCanonicalPath() + "\\myFile.encrypted"));
    }

    @Test
    public void preformFunctionShouldWriteToScreen() {
        decryptor.run();
        assertThat(outContent.toString(), is("decryption simulation of file " + decryptor.getFilePath() + "\r\n"));
    }

    @Test
    public void DecryptEmptyFilePathShouldDoNothing() throws IOException{
        decryptor.setFilePath("");

        decryptor.run();

        if(!decrypted.exists()) //file does not exist
            return;
    }

    @Test
    public void DecryptNoneShouldNotChangeTheFileContent() throws IOException {
        decryptor.setAlgorithmType(AlgorithmTypeEnum.NONE);

        //write test data to file
        PrintWriter writer = new PrintWriter(testFilePath, "UTF-8");
        writer.println(fileContentDecrypted);
        writer.close();

        decryptor.run();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine(), is(fileContentDecrypted));
    }

    @Test
    public void DecryptCaesarShouldEncryptTheFileContent() throws IOException {
        decryptor.setAlgorithmType(AlgorithmTypeEnum.CAESAR);

        //write test data to file
        PrintWriter writer = new PrintWriter(testFilePath, "UTF-8");
        writer.println(fileContentCaesarEncrypted);
        writer.close();

        decryptor.run();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine(), is(fileContentDecrypted));
    }

}