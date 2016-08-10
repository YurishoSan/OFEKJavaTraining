package encryption;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import encryption.algorithms.NoneEncryptionAlgorithmDecorator;
import encryption.algorithms.ObservableEncryptionAlgorithmDecorator;
import encryption.algorithms.BasicAlgorithm;
import encryption.design.observer.EventTypesEnum;
import encryption.exception.EncryptionException;
import encryption.exception.EndEventCalledBeforeStartEventException;
import lombok.*;
import utils.FileUtils;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Abstract encryption function.
 *
 * @author Yitzhak Goldstein
 * @version 5.1
 */
@Data public abstract class EncryptionFunction implements Runnable {
    // Constants -------------------------------------------------------------------------------------------------------
    public static final int BYTE_MAX_VALUE = 255;


    // Attributes ------------------------------------------------------------------------------------------------------
    /**
     * path of file to preform function
     * @since 1.1
     */
    private String filePath;
    private ObservableEncryptionAlgorithmDecorator algorithm;
    private boolean batchMode;
    // Contors ---------------------------------------------------------------------------------------------------------

    /**
     * default contor
     * filePath defaults to empty string
     * key defaults to 0
     * @since 1.1
     */
    public EncryptionFunction() {
        setFilePath("");
        setAlgorithm(new NoneEncryptionAlgorithmDecorator(new BasicAlgorithm(), (char)0));
        setBatchMode(false);
    }

    /**
     * contor
     * @since 1.0
     * @param filePath path of file to preform function
     * @param algorithm Type of Algorithm to use
     */
    public EncryptionFunction(String filePath, ObservableEncryptionAlgorithmDecorator algorithm) {

        setFilePath(filePath);
        setAlgorithm(algorithm);
        setBatchMode(false);
    }

    /**
     * contor
     * @since 5.1
     * @param filePath path of file to preform function
     * @param algorithm Type of Algorithm to use
     * @param batchMode is the function running on a full directory
     */
    public EncryptionFunction(String filePath, ObservableEncryptionAlgorithmDecorator algorithm, boolean batchMode) {

        setFilePath(filePath);
        setAlgorithm(algorithm);
        setBatchMode(batchMode);
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
     */
    public final void run() {
        if (getFilePath().equals(""))
            return;

        File inputFile, outputFile;

        try {
            inputFile = new File(getInputFileName());
            outputFile = new File(getOutputFileName());

            algorithm.setInputFile(inputFile);
            algorithm.setOutputFile(outputFile);

            algorithm.notifyObservers(EventTypesEnum.FUNCTION_START);
            algorithm.init();
            algorithm.algorithm();
            algorithm.notifyObservers(EventTypesEnum.FUNCTION_END);

        } catch (IOException | EncryptionException exp) {
            System.out.println(exp.getMessage());
            System.out.println(Arrays.toString(exp.getStackTrace()));

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
    /*private static void close(Closeable c) {
        if (c == null) return;
        try {
            c.close();
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
            System.out.println(Arrays.toString(exp.getStackTrace()));
        }
    }*/

    /**
     * gets the path of the file to input
     * @return full path of the file to input
     */
    protected abstract String getInputFileName();

    /**
     * gets the path of the file to output
     * @return full path of the file to output
     */
    protected abstract String getOutputFileName();

    public boolean getBatchMode() {
        return batchMode;
    }
}
