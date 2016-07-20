/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.2
 */
public class Encryptor extends EncryptionFunction{

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
    public void PreformFunction() {
        Encrypt();
    }

    /**
     * mock encryption of file.
     * prints that the file is being encrypted.
     * @since 1.0
     */
    public void Encrypt() {
        /*
        Encrypt pseudo code
            print("encryption simulation of file " + filePath")
         */
        System.out.println("encryption simulation of file " + filePath);
    }
}
