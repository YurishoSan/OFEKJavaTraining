import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms caesar encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class CaesarEncryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {

    public CaesarEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException {
        /* algorithm pseudo code
            for-each byte originalByte in original
                    encryptedByte <- originalByte + key with overflow
                    write encryptedByte to file encrypted
         */

        int c;

        while ((c = original.read()) != -1) {
            int value = c + key;
            while (value > EncryptionFunction.BYTE_MAX_VALUE)
                value = value - EncryptionFunction.BYTE_MAX_VALUE - 1;
            encrypted.write(value & (0xff));
        }
        encrypted.close();
    }
}
