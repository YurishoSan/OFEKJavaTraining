import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Mathematical utilities
 *
 * @author Yitzhak Goldstein
 * @version 0.1
 */
public class MathUtils {

    /**
     * private contor to prevent instantiating of the class
     */
    private MathUtils() {

    }

    /**
     * preforms Multiplication with overflow on 2 numbers
     * the numbers are multiplied, then converted to byte to drop all bits higher then 8
     * @param n1 first number
     * @param n2 second number
     * @return result of: n1 MWO n2
     */
    @org.jetbrains.annotations.Contract(pure = true)
    public static char MWO(char n1, char n2) {
        /* MWO pseudo code
            result <- (n1 * n2) as byte
            return result
         */

        return (char)(byte)(n1 * n2); // convert to byte to take care of overflow. then to char to return it.
    }
}
