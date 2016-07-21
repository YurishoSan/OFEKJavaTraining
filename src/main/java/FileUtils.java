//source: http://www.rgagnon.com/javadetails/java-check-if-a-filename-is-valid.html

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static boolean isFilenameValid(String file) {
        File f = new File(file);
        try {
            f.getCanonicalPath();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }
}
