import lombok.*;

import java.io.File;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 2.0
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
     */
    public Encryptor(String filePath) {
        super(filePath);
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
     * mock encryption of file.
     * prints that the file is being encrypted.
     * @since 1.0
     */
    private void Encrypt() {
        /*
        Encrypt pseudo code
            print("encryption simulation of file " + filePath")

            original <- FileAt(filePath)
            encrypted <- FileAt(filePath + ".encrypted")

            key <- Random()
            print (key)

            Algorithm(CAESAR, original, encrypted, key)
         */
        System.out.println("encryption simulation of file " + filePath);
    }

    /**
     * Apply Encryption Algorithm to original file, and out put an encrypted file
     *
     * @since 2.0
     *
     * @param algorithmType  Type of Algorithm to use
     * @param original file to encrypt
     * @param encrypted output encrypted file
     * @param key key to use in encryption
     */
    protected void Algorithm(AlgorithmTypeEnum algorithmType, File original, File encrypted, byte key) {
    /*
    algorithm pseudo code
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
    }
}
