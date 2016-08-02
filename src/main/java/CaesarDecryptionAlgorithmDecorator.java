import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms caesar decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class CaesarDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {
    public CaesarDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream encrypted, FileOutputStream decrypted, byte key) throws IOException {
        /* algorithm pseudo code
            for-each byte encryptedByte in encrypted
                       decryptedByte <- encryptedByte - key with underflow
                       write decryptedByte to file decrypted
        */

        int c;

        while ((c = encrypted.read()) != -1) {
            int value = c - key;
            while (value < 0)
                value = Byte.MAX_VALUE + value + 1;
            decrypted.write((byte)value);
        }
    }
}
