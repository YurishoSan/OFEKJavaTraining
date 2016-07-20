/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class Encryptor extends EncryptionFunction{

    // Contors ---------------------------------------------------------------------------------------------------------
    /**
     * contor
     * @param filePath path of file to encrypt
     */
    public Encryptor(String filePath) {
        super(filePath);
    }

    // Methods ---------------------------------------------------------------------------------------------------------

    /**
     * mock encryption of file.
     * prints that the file is being encrypted.
     */
    public void Encrypt() {
        /*
        Encrypt pseudo code
            print("encryption simulation of file " + filePath")
         */
        System.out.println("encryption simulation of file " + filePath);
    }
}
