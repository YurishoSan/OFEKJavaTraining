import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 21/07/2016.
 */
public class DecryptorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private Decryptor decryptor;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpDecryptor() throws IOException {
        folder.newFile("test.txt");
        decryptor = new Decryptor(folder.getRoot().getCanonicalPath() + "\\test.txt");
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
    public void preformFunctionShouldWriteToScreen() {
        decryptor.PreformFunction();
        assertThat(outContent.toString(), is("decryption simulation of file " + decryptor.GetFilePath() + "\r\n"));
    }

}