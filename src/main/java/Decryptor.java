import lombok.*;

import java.io.File;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 2.2
 */
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
     * @param algorithmType  Type of Algorithm to use
     */
    public Decryptor(String filePath, byte key, AlgorithmTypeEnum algorithmType) {
        super(filePath, key, algorithmType);
    }

    // Methods ---------------------------------------------------------------------------------------------------------

    /**
     * call Decrypt
     * @since 1.1
     * @see #Decrypt()
     */
    protected void PreformFunction() {
        Decrypt();
    }

    /**
     * decrypt the file.
     * @since 1.0
     */
    private void Decrypt() {
        /*
        Encrypt pseudo code
            print("decryption simulation of file " + filePath)

            encrypted <- FileAt(filePath)
            decrypted <- FileAt(filePath - ".encrypted" + "_decrypted" + [original-file-extension])

            switch(algorithmType)
               case NONE:
                   copy encrypted file to decrypted file
                   break

               case CAESAR:
                   for-each byte encryptedByte in encrypted
                       decryptedByte <- encryptedByte - key with underflow
                       write decryptedByte to file decrypted
                   break
         */

        System.out.println("decryption simulation of file " + getFilePath());
    }
}
