import lombok.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 2.5
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
     * @param algorithmType  Type of Algorithm to use
     */
    public Decryptor(String filePath, byte key, AlgorithmTypeEnum algorithmType) {
        super(filePath, key, algorithmType);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------
    /**
     * validates the value is a valid path with '.encrypted' extension
     * if validation fails the function returns without doing anything.
     * @since 2.2
     * @param value value to set filePath
     */
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

    // Methods ---------------------------------------------------------------------------------------------------------

    /**
     * call Decrypt
     * @since 1.1
     * @see #Decrypt()
     */
    protected void PreformFunction() throws IOException {
        Decrypt();
    }

    /**
     * decrypt the file.
     * @since 1.0
     */
    private void Decrypt() throws IOException{
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
        String originalFilePath;
        String testFilePathWOExtension;
        String testFilePathExtension;

        System.out.println("decryption simulation of file " + getFilePath());

        if (getFilePath().equals(""))
            return;

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

        FileInputStream encrypted = null;
        FileOutputStream decrypted = null;

        try {
            encrypted = new FileInputStream(getFilePath());
            decrypted = new FileOutputStream(testFilePathWOExtension + "_decrypted" + testFilePathExtension);
            int c;

            switch(getAlgorithmType()) {
                case NONE:
                    while ((c = encrypted.read()) != -1) {
                        decrypted.write(c);
                    }
                    break;
                case CAESAR:
                    while ((c = encrypted.read()) != -1) {
                        int value = c - getKey();
                        while (value < 0)
                            value = Byte.MAX_VALUE + value + 1;
                        decrypted.write((byte)value);
                    }
                    break;
            }
        } finally {
            if (encrypted != null) {
                encrypted.close();
            }
            if (decrypted != null) {
                decrypted.close();
            }
        }
    }
}
