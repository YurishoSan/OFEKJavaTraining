import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 21/07/2016.
 *
 * test EncryptionFunction class
 */
public class EncryptionFunctionTest {
    private EncryptionFunction encryptionFunction;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUpEncryptionFunction() {
        //build a generic encryptionFunction
        encryptionFunction = new EncryptionFunction() {
            @Override
            protected void PreformFunction() {
            }
        };
    }

    @After
    public void tearDownEncryptionFunction() {
        encryptionFunction = null;
    }

    @Test
    public void setFilePathShouldFail() throws IOException {
        folder.newFolder("myFolder");

        encryptionFunction.setFilePath(folder.getRoot().getCanonicalPath() + "\\myFolder\\test.T*T"); //non valid file
        assertThat(encryptionFunction.getFilePath(), is(""));

        encryptionFunction.setFilePath(folder.getRoot().getCanonicalPath() + "\\myFolder\\notMyFile.txt"); //non existent file
        assertThat(encryptionFunction.getFilePath(), is(""));

        encryptionFunction.setFilePath(folder.getRoot().getCanonicalPath() + "\\MyFolder"); //a folder
        assertThat(encryptionFunction.getFilePath(), is(""));
    }

    @Test
    public void setFilePathShouldSucceed() throws IOException {
        folder.newFile("myFile.txt");

        encryptionFunction.setFilePath(""); //empty path value
        assertThat(encryptionFunction.getFilePath(), is(""));

        encryptionFunction.setFilePath(folder.getRoot().getCanonicalPath() + "\\myFile.txt");
        assertThat(encryptionFunction.getFilePath(), is(folder.getRoot().getCanonicalPath() + "\\myFile.txt"));
    }

}