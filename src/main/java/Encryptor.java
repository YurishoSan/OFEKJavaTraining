import lombok.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 2.3
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
    protected void PreformFunction() throws IOException {
        Encrypt();
    }

    /**
     * encrypt the file.
     * prints that the file is being encrypted.
     * @since 1.0
     */
    private void Encrypt() throws IOException{
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

        FileInputStream original = null;
        FileOutputStream encrypted = null;

        try {
            original = new FileInputStream(getFilePath());
            encrypted = new FileOutputStream(getFilePath() + ".encrypted");
            int c;

            switch(getAlgorithmType()) {
                case NONE:
                    while ((c = original.read()) != -1) {
                        encrypted.write(c);
                    }
                    break;
                case CAESAR:
                    while ((c = original.read()) != -1) {
                        /* overflow wrapping is handled by java as per java specifications
                        ("The integer operators do not indicate overflow or underflow in any way.")
                        source: http://docs.oracle.com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.2 */
                        encrypted.write((byte)c + getKey());
                    }
                    break;
            }
        } finally {
            if (original != null) {
                original.close();
            }
            if (encrypted != null) {
                encrypted.close();
            }
        }
    }
}
