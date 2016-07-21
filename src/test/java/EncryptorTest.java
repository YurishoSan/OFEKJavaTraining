import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 21/07/2016.
 */
public class EncryptorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private Encryptor encryptor;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpEncryptor() throws IOException {
        folder.newFile("test.txt");
        encryptor = new Encryptor(folder.getRoot().getCanonicalPath() + "\\test.txt");
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
        encryptor.PreformFunction();
        assertThat(outContent.toString(), is("encryption simulation of file " + encryptor.GetFilePath() + "\r\n"));
    }

}