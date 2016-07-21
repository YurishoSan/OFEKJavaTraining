import java.io.File;
import lombok.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Abstract encryption function.
 *
 * @author Yitzhak Goldstein
 * @version 2.0
 */
@Data public abstract class EncryptionFunction implements Runnable{
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
        setFilePath(filePath);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------
    /**
     * validates the value is a valid path, and that it is not a directory and that it exists, and sets filePath to value.
     * if validation fails the function returns without doing anything.
     * @since 1.0
     * @param value
     */
    public void setFilePath(String value) {
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

    public void run() {
        PreformFunction();
    }

    protected abstract void PreformFunction();

    /**
     * Apply Algorithm to original file, and output an output file
     *
     * @since 2.0
     *
     * @param algorithmType Algorithm to apply
     * @param originalFile file to use algorithm on
     * @param outputFile output file
     * @param key key to use algorithm with
     */
    protected abstract void Algorithm(AlgorithmTypeEnum algorithmType, File originalFile, File outputFile, byte key);
}
