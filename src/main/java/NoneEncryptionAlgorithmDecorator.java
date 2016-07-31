import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class NoneEncryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {

    public NoneEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream original, FileOutputStream encrypted, byte key) throws IOException {
        /* decrypt pseudo code
            copy original file to encrypted file
         */
        super.algorithm(original, encrypted, key);
        int c;

        while ((c = original.read()) != -1) {
            encrypted.write(c);
        }
    }
}
