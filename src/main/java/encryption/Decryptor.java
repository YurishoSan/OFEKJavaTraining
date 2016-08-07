package encryption;

import encryption.algorithms.ObservableEncryptionAlgorithmDecorator;
import encryption.exception.IllegalKeyException;
import lombok.*;

import java.io.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 4.0
 */
@EqualsAndHashCode(callSuper = true)
@Data public class Decryptor extends EncryptionFunction{
    // Contors ---------------------------------------------------------------------------------------------------------
    /**
     * default contor
     */
    public Decryptor() {
        super();
    }

    /**
     * contor
     * @since 1.0
     * @param filePath path of file to encrypt
     * @param key the key for the encryption
     * @param algorithm  Type of Algorithm to use
     */
    public Decryptor(String filePath, char key, ObservableEncryptionAlgorithmDecorator algorithm) {
        super(filePath, key, algorithm);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------
    /**
     * validates the value is a valid path with '.encrypted' extension
     * if validation fails the function returns without doing anything.
     * @since 2.2
     * @param value value to set filePath
     */
    @Override
    public void setFilePath(String value) {
        /*
        SetFilePath pseudo code
            super.setFilePath(value)
            if (extension not '.encrypted')
                setFilePath("")
         */
        super.setFilePath(value);
        if (!(getFilePath() != null && getFilePath().contains(".")) || // if has no extension
                !(getFilePath().substring(getFilePath().lastIndexOf(".")).equals(".encrypted"))) // or extension is not ".encrypted"
            super.setFilePath("");
    }

    /**
     * gets the path of the file to decrypt
     * @return full path of the file to decrypt
     */
    @Override
    protected String getInputFileName() {
        return getFilePath();
    }

    /**
     * gets the path of the file to write the decrypted output to
     * @return full path of the decrypted file
     */
    @Override
    protected String getOutputFileName() {
        String originalFilePath;
        String testFilePathWOExtension;
        String testFilePathExtension;

        //get file names
        originalFilePath = getFilePath().substring(0, getFilePath().lastIndexOf('.')); // remove '.encrypted', duo to setFilePath override, there must be a '.encrypted'
        if(getFilePath() != null && getFilePath().contains(".")) { // if there is an extension
            testFilePathWOExtension = originalFilePath.substring(0, originalFilePath.lastIndexOf('.'));
            testFilePathExtension = originalFilePath.substring(originalFilePath.lastIndexOf("."));
        }
        else { //no extension
            testFilePathWOExtension = originalFilePath;
            testFilePathExtension = "";
        }

        return testFilePathWOExtension + "_decrypted" + testFilePathExtension;
    }
}
