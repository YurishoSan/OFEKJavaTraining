import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms XOR decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class XorDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator{
    public XorDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream encrypted, FileOutputStream decrypted, byte key) throws IOException {
         /* algorithm pseudo code
            for-each byte encryptedByte in encrypted
                       decryptedByte <- encryptedByte ^ key
                       write decryptedByte to file decrypted
        */
    }
}
