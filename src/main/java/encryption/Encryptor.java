package encryption;

import encryption.algorithms.ObservableEncryptionAlgorithmDecorator;
import encryption.exception.IllegalKeyException;
import lombok.*;

import java.io.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 4.0
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
     * @param key the key for the encryption
     * @param algorithm  Type of Algorithm to use
     */
    public Encryptor(String filePath, char key, ObservableEncryptionAlgorithmDecorator algorithm) {
        super(filePath, key, algorithm);
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
        return getFilePath() + ".encrypted";
    }
}
