import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms Multiplication decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class MultiplicationDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {
    public MultiplicationDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream original, FileOutputStream encrypted, byte key) throws IOException {
        /* algorithm pseudo code
            decryptionKey <- FindDecryptionKey()

            for-each byte encryptedByte in encrypted
                    decryptedByte <- encryptedByte MWO decryptionKey
                    write decryptedByte to file decrypted
        */
    }
}
