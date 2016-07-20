/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms encryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class Encryptor {

    // Attributes ------------------------------------------------------------------------------------------------------
    /**
     * path of file to decrypt
     */
    String filePath;

    // Contors ---------------------------------------------------------------------------------------------------------
    /**
     * contor
     * @param filePath path of file to encrypt
     */
    public Encryptor(String filePath) {
        SetFilePath(filePath);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------

    public String GetFilePath() {
        return filePath;
    }

    public void SetFilePath(String value) {
        filePath = value;
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
