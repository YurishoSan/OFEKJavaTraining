import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class NoneDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator{

    public NoneDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream encrypted, FileOutputStream decrypted, byte key) throws IOException {
        /* decrypt pseudo code
            copy encrypted file to decrypted file
         */
        super.algorithm(encrypted, decrypted, key);
        int c;

        while ((c = encrypted.read()) != -1) {
            decrypted.write(c);
        }
    }
}
