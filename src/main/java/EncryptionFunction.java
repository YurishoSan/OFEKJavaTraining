import java.io.File;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Abstract encryption function.
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class EncryptionFunction {
    // Attributes ------------------------------------------------------------------------------------------------------
    /**
     * path of file to preform function
     */
    String filePath;

    // Contors ---------------------------------------------------------------------------------------------------------
    /**
     * contor
     * @param filePath path of file to preform function
     */
    public EncryptionFunction(String filePath) {
        SetFilePath(filePath);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------

    /**
     * get the file path
     * @return the file path
     */
    public String GetFilePath() {
        return filePath;
    }

    /**
     * validates the value is a valid path, and that it is not a directory and that it exists, and sets filePath to value.
     * if validation fails the function returns without doing anything.
     * @param value
     */
    public void SetFilePath(String value) {
        /*
        SetFilePath pseudo code
            if (value is illegal path or file at value does not exist or is directory)
                return

            filePath = value
         */
        File file = new File(filePath);
        if (!FileUtils.isFilenameValid(value) || !file.exists() || file.isDirectory())
            return;
        filePath = value;
    }
}
