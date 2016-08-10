package encryption;

import encryption.algorithms.ObservableEncryptionAlgorithmDecorator;
import encryption.exception.IllegalKeyException;
import lombok.*;

import java.io.*;
import java.util.List;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 5.1
 */
@EqualsAndHashCode(callSuper = true)
@Data public class Encryptor extends EncryptionFunction{

    // Contors ---------------------------------------------------------------------------------------------------------
    /**
     * default contor
     */
    public Encryptor() {
        super();
    }

    /**
     * contor
     * @since 1.0
     * @param filePath path of file to encrypt
     * @param algorithm  Type of Algorithm to use
     */
    public Encryptor(String filePath, ObservableEncryptionAlgorithmDecorator algorithm) {
        super(filePath, algorithm);
    }

    @Override
    public Encryptor clone() {
        try {
            return (Encryptor) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    /**
     * gets the path of the file to encrypt
     * @return full path of the file to encrypt
     */
    @Override
    protected String getInputFileName() {
        return getFilePath();
    }

    /**
     * gets the path of the file to write the encrypted output to
     * @return full path of the encrypted file
     */
    @Override
    protected String getOutputFileName() {
        if (getBatchMode())
            return getFilePath().substring(0, getFilePath().lastIndexOf("\\")) + "\\encrypted\\" + getFilePath().substring(getFilePath().lastIndexOf("\\")+1);

        return getFilePath() + ".encrypted";
    }
}
