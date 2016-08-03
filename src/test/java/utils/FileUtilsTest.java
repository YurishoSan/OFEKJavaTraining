package utils;

import org.junit.Test;
import utils.FileUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 21/07/2016.
 *
 * Test utils.FileUtils class
 */
public class FileUtilsTest {
    @Test
    public void isFilenameValidShouldReturnTrue() {
        assertThat(FileUtils.isFilenameValid("well.txt"), is(true));
        assertThat(FileUtils.isFilenameValid("well well.txt"), is(true));
        assertThat(FileUtils.isFilenameValid(""), is(true));
    }

    @Test
    public void isFileNameValidShouldReturnFalse() {
        assertThat(FileUtils.isFilenameValid("test.T*T"), is(false));
        assertThat(FileUtils.isFilenameValid("test|.TXT"), is(false));
        assertThat(FileUtils.isFilenameValid("te?st.TXT"), is(false));
    }

}