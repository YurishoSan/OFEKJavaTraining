/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.2
 */
public class Decryptor extends EncryptionFunction{

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
     * @param filePath path of file to decrypt
     */
    public Decryptor(String filePath) {
        super(filePath);
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
         */
        System.out.println("decryption simulation of file " + filePath);
    }
}
