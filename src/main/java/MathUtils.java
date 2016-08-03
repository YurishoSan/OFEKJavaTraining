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

    private MathUtils() {

    }

    public static char MWO(char n1, char n2) {
        /* MWO pseudo code
            result <- (n1 * n2) as byte
            return result
         */

        char result = (char)(byte)(n1 * n2); // convert to byte to take care of overflow. then to char to return it.
        return result;
    }
}
