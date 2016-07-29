import java.io.File;
import java.io.IOException;

import lombok.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Abstract encryption function.
 *
 * @author Yitzhak Goldstein
 * @version 2.4
 */
@Data public abstract class EncryptionFunction implements Runnable{
    // Attributes ------------------------------------------------------------------------------------------------------
    /**
     * path of file to preform function
     * @since 1.1
     */
    private String filePath;
    private byte key;
    private AlgorithmTypeEnum algorithmType;

    // Contors ---------------------------------------------------------------------------------------------------------

    /**
     * default contor
     * filePath defaults to empty string
     * key defaults to 0
     * @since 1.1
     */
    public EncryptionFunction() {
        filePath = "";
        key = 0;
        algorithmType = AlgorithmTypeEnum.NONE;
    }

    /**
     * contor
     * @since 1.0
     * @param filePath path of file to preform function
     * @param key the key for the encryption
     * @param algorithmType  Type of Algorithm to use
     */
    public EncryptionFunction(String filePath, byte key, AlgorithmTypeEnum algorithmType) {

        setFilePath(filePath);
        setKey(key);
        setAlgorithmType(algorithmType);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------
    /**
     * validates the value is a valid path, and that it is not a directory and that it exists, and sets filePath to value.
     * if validation fails the function returns without doing anything.
     * @since 1.0
     * @param value value to set filePath
     */
    public void setFilePath(String value) {
        /*
        SetFilePath pseudo code
            if (value is illegal path or file at value does not exist or is directory)
                return

            filePath = value
         */
        File file = new File(value);
        if (!value.equals("") && (!FileUtils.isFilenameValid(value) || !file.exists() || file.isDirectory()))
            return;
        filePath = value;
    }

    public void run() {
        try {
            PreformFunction();
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        }
    }

    protected abstract void PreformFunction() throws IOException;
}
