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
 *
 * Test Decryptor class
 */
public class DecryptorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private File encrypted;
    private File decrypted;

    private Decryptor decryptor;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpDecryptor() throws IOException {
        String testFilePath;
        final char key = 10;
        final String fileName = "text.txt";

        testFilePath = folder.getRoot().getCanonicalPath() + "\\" + fileName;
        String testFilePathWOExtension = testFilePath.substring(0, testFilePath.lastIndexOf('.'));
        String testFilePathExtension = testFilePath.substring(testFilePath.lastIndexOf("."));
        folder.newFile(fileName + ".encrypted");
        encrypted = new File(testFilePath + ".encrypted");
        decrypted = new File(testFilePathWOExtension + "_decrypted" + testFilePathExtension);

        decryptor = new Decryptor(testFilePath + ".encrypted", key, AlgorithmTypeEnum.NONE);
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

        if(decrypted.exists())
            fail();
    }

    /*@Test
    public void DecryptNoneShouldNotChangeTheFileContent() throws IOException {
        decryptor.setAlgorithmType(AlgorithmTypeEnum.NONE);

        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentDecrypted);
        writer.close();

        decryptor.run();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine(), is(fileContentDecrypted));
    }

    @Test
    public void DecryptCaesarShouldDecryptTheFileContent() throws IOException {
        byte[] fileContentCaesarEncryptedByteArray = {0x52,0x6f,0x76,0x76,0x79,0x36,0x2a,0x01,0x79,0x7c,0x76,0x6e,0x2b};
        String fileContentCaesarEncrypted = new String(fileContentCaesarEncryptedByteArray);
        decryptor.setAlgorithmType(AlgorithmTypeEnum.CAESAR);

        //write test data to file
        PrintWriter writer = new PrintWriter(encrypted, "UTF-8");
        writer.println(fileContentCaesarEncrypted);
        writer.close();

        decryptor.run();

        BufferedReader decryptedReader = new BufferedReader(new FileReader(decrypted));

        assertThat(decryptedReader.readLine().substring(0,13), is(fileContentDecrypted)); // use substring to remove extra bytes at end of file
    }*/

}