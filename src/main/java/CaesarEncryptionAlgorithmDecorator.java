import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms caesar encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class CaesarEncryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {

    public CaesarEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream original, FileOutputStream encrypted, byte key) throws IOException {
        /* encrypt pseudo code
            for-each byte originalByte in original
                    encryptedByte <- originalByte + key with overflow
                    write encryptedByte to file encrypted
         */

        int c;

        while ((c = original.read()) != -1) {
            int value = c +key;
            while (value > Byte.MAX_VALUE)
                value = value - Byte.MAX_VALUE - 1;
            encrypted.write((byte)value);
        }
    }
}
