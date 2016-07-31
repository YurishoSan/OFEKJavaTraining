import lombok.*;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 2.5
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
    public Encryptor(String filePath, byte key, AlgorithmTypeEnum algorithmType) {
        super(filePath, key, algorithmType);
    }

    @Override
    protected String getInputFileName() {
        return getFilePath();
    }

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
    public void algorithm(FileInputStream original, FileOutputStream encrypted, byte key) throws IOException{
        /*
        algorithm pseudo code
            print("encryption simulation of file " + filePath)
         */
        System.out.println("encryption simulation of file " + getFilePath());
    }
}
