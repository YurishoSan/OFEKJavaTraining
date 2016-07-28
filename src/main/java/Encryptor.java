import lombok.*;

import java.io.File;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 2.2
 */
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

    // Methods ---------------------------------------------------------------------------------------------------------

    /**
     * call Encrypt
     * @since 1.1
     * @see #Encrypt()
     */
    protected void PreformFunction() {
        Encrypt();
    }

    /**
     * encrypt the file.
     * prints that the file is being encrypted.
     * @since 1.0
     */
    private void Encrypt() {
        /*
        Encrypt pseudo code
            print("encryption simulation of file " + filePath")

            original <- FileAt(filePath)
            encrypted <- FileAt(filePath + ".encrypted")

            switch(algorithmType)
            case NONE:
                copy original file to encrypted file
                break

            case CAESAR:
                for-each byte originalByte in original
                    encryptedByte <- originalByte + key with overflow
                    write encryptedByte to file encrypted
                break
         */
        System.out.println("encryption simulation of file " + getFilePath());
    }
}
