//source: http://www.rgagnon.com/javadetails/java-check-if-a-filename-is-valid.html

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    /**
     * check if the file name is valid
     * @param file file name to check
     * @return is file name valid
     */
    public static boolean isFilenameValid(String file) {
        File f = new File(file);
        try {

            //noinspection ResultOfMethodCallIgnored
            f.getCanonicalPath();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
