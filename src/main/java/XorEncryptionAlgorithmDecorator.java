import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms XOR encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class XorEncryptionAlgorithmDecorator  extends EncryptionAlgorithmDecorator {
    public XorEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream original, FileOutputStream encrypted, byte key) throws IOException {
        /* algorithm pseudo code
            for-each byte originalByte in original
                    encryptedByte <- originalByte ^ key
                    write encryptedByte to file encrypted
         */
    }
}
