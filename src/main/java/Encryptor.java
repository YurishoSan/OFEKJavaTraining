import lombok.*;

import java.io.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 3.0
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
     * @param algorithmType  Type of Algorithm to use
     */
    public Encryptor(String filePath, char key, AlgorithmTypeEnum algorithmType) {
        super(filePath, key, algorithmType);
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

    // Methods ---------------------------------------------------------------------------------------------------------
    /**
     * encrypt the file.
     * prints that the file is being encrypted.
     * @since 2.5
     */
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException, IllegalKeyException{
        /*
        algorithm pseudo code
            print("encryption simulation of file " + filePath)
         */
        super.algorithm(original, encrypted, key);

        System.out.println("encryption simulation of file " + getFilePath());
    }
}
