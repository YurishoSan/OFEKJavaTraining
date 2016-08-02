import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms caesar decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class CaesarDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {
    public CaesarDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    @Override
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException {
        /* algorithm pseudo code
            for-each byte encryptedByte in encrypted
                       decryptedByte <- encryptedByte - key with underflow
                       write decryptedByte to file decrypted
        */

        int c;

        while ((c = encrypted.read()) != -1) {
            int value = c - key;
            while (value < 0)
                value = EncryptionFunction.BYTE_MAX_VALUE + value + 1;
            decrypted.write(value & (0xff));
        }
        decrypted.close();
    }
}
