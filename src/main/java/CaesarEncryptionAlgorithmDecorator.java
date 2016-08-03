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

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public CaesarEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    /**
     * apply caesar algorithm for encryption on the original and write the result to the encrypted
     * @param original file to apply the algorithm to
     * @param encrypted file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
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
