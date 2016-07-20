/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class Decryptor extends EncryptionFunction{

    // Contors ---------------------------------------------------------------------------------------------------------
    /**
     * contor
     * @param filePath path of file to decrypt
     */
    public Decryptor(String filePath) {
        super(filePath);
    }

    // Methods ---------------------------------------------------------------------------------------------------------

    /**
     * mock decryption of file.
     * prints that the file is being decrypted.
     */
    public void Decrypt() {
        /*
        Encrypt pseudo code
            print("decryption simulation of file " + filePath)
         */
        System.out.println("decryption simulation of file " + filePath);
    }
}
