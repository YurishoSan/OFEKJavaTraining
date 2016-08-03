package utils;

import org.junit.Test;
import utils.MathUtils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Test utils.MathUtils class
 */
public class MathUtilsTest {
    @Test
    public void MWOShouldMultiplySmallNumbersNormally() {
        char result = MathUtils.MWO((char)5,(char)3);

        assertThat(result, is((char)15));
    }

    @Test
    public void MWOShouldMultiplyBigNumbersWithOverFlow() {
        char result = MathUtils.MWO((char)50, (char)6);
        assertThat(result, is((char)44));
    }

}