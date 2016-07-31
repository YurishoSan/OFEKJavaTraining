import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import lombok.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Abstract encryption function.
 *
 * @author Yitzhak Goldstein
 * @version 2.5
 */
@Data public abstract class EncryptionFunction implements Runnable, EncryptionAlgorithm{
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

    /**
     * call algorithm function
     * @since 1.1
     * @see #algorithm(FileInputStream, FileOutputStream, byte)
     */
    public void run() {
        if (getFilePath().equals(""))
            return;

        FileInputStream inputFile = null;
        FileOutputStream outputFile = null;

        try {
            inputFile = new FileInputStream(getInputFileName());
            outputFile = new FileOutputStream(getOutputFileName());

            algorithm(inputFile, outputFile, getKey());

        } catch (IOException exp) {
            System.out.println(exp.getMessage());
            System.out.println(Arrays.toString(exp.getStackTrace()));

        } finally {
            close(inputFile);
            close(outputFile);
        }
    }

    /**
     * Closes a closeable object
     *
     * See: http://stackoverflow.com/questions/2699209/java-io-ugly-try-finally-block
     *
     * @param c the closeable object to close
     * @since 2.5
     */
    private static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
            System.out.println(Arrays.toString(exp.getStackTrace()));
        }
    }

    protected abstract String getInputFileName();
    protected abstract String getOutputFileName();
}
