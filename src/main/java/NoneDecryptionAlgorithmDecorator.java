import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class NoneDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator{

    public NoneDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    @Override
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException {
        /* algorithm pseudo code
            copy encrypted file to decrypted file
         */
        super.algorithm(encrypted, decrypted, key);
        int c;

        while ((c = encrypted.read()) != -1) {
            decrypted.write(c & (0xff));
        }
        decrypted.close();
    }
}
