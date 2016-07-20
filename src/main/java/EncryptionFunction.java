import java.io.File;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Abstract encryption function.
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public abstract class EncryptionFunction {
    // Attributes ------------------------------------------------------------------------------------------------------
    /**
     * path of file to preform function
     * @since 1.1
     */
    String filePath;

    // Contors ---------------------------------------------------------------------------------------------------------

    /**
     * default contor
     * filePath defaults to empty string
     *
     * @since 1.1
     */
    public EncryptionFunction() {
        filePath = "";
    }

    /**
     * contor
     * @since 1.0
     * @param filePath path of file to preform function
     */
    public EncryptionFunction(String filePath) {
        SetFilePath(filePath);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------

    /**
     * get the file path
     * @since 1.0
     * @return the file path
     */
    public String GetFilePath() {
        return filePath;
    }

    /**
     * validates the value is a valid path, and that it is not a directory and that it exists, and sets filePath to value.
     * if validation fails the function returns without doing anything.
     * @since 1.0
     * @param value
     */
    public void SetFilePath(String value) {
        /*
        SetFilePath pseudo code
            if (value is illegal path or file at value does not exist or is directory)
                return

            filePath = value
         */
        File file = new File(value);
        if (!FileUtils.isFilenameValid(value) || !file.exists() || file.isDirectory())
            return;
        filePath = value;
    }

    public void RunFunction() {
        Runnable function = new Runnable() {

            public void run() {
                PreformFunction();
            }
        };

        function.run();
    }

    protected abstract void PreformFunction();
}
