import lombok.*;

import java.io.File;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.3
 */
@Data public class Decryptor extends EncryptionFunction{

    private int key;

    // Contors ---------------------------------------------------------------------------------------------------------
    /**
     * default contor
     */
    public Decryptor() {
        super();
        key = 0;
    }

    /**
     *contor
     * @since 1.0
     * @param filePath path of file to decrypt
     * @param key key to decrypt with
     */
    public Decryptor(String filePath, int key) {
        super(filePath);
        setKey(key);
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
     * mock decryption of file.
     * prints that the file is being decrypted.
     * @since 1.0
     */
    private void Decrypt() {
        /*
        Encrypt pseudo code
            print("decryption simulation of file " + filePath)

            encrypted <- FileAt(filePath)
            decrypted <- FileAt(filePath - ".encrypted" + "_decrypted" + [original-file-extension])

            Algorithm(CAESAR, encrypted, decrypted, key)
         */
        System.out.println("decryption simulation of file " + filePath);
    }

    /**
     * Apply Decryption Algorithm to encrypted file, and out put a decrypted file
     *
     * @since 2.0
     *
     * @param algorithmType  Type of Algorithm to use
     * @param encrypted encrypted file
     * @param decrypted output decrypted file
     * @param key key to use in decryption
     */
    protected void Algorithm(AlgorithmTypeEnum algorithmType, File encrypted, File decrypted, byte key) {
        /*
        algorithm pseudo code
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
    }
}
