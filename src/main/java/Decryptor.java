import lombok.*;

import java.io.*;

/**
 * Created by yurisho on 20/07/2016.
 *
 * Preforms decryption of files.
 *
 * @author Yitzhak Goldstein
 * @version 3.0
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
    public Decryptor(String filePath, char key, AlgorithmTypeEnum algorithmType) {
        super(filePath, key, algorithmType);
    }

    // Getters/Setters -------------------------------------------------------------------------------------------------
    /**
     * validates the value is a valid path with '.encrypted' extension
     * if validation fails the function returns without doing anything.
     * @since 2.2
     * @param value value to set filePath
     */
    @Override
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

    @Override
    protected String getInputFileName() {
        return getFilePath();
    }

    @Override
    protected String getOutputFileName() {
        String originalFilePath;
        String testFilePathWOExtension;
        String testFilePathExtension;

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

        return testFilePathWOExtension + "_decrypted" + testFilePathExtension;
    }

    // Methods ---------------------------------------------------------------------------------------------------------

    /**
     * decrypt the file.
     * prints that the file is being encrypted.
     * @since 2.6
     */
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException {
        /*
        algorithm pseudo code
            print("decryption simulation of file " + filePath)
         */
        System.out.println("decryption simulation of file " + getFilePath());
    }
}
