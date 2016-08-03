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
    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public CaesarDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    /**
     * apply caesar algorithm for decryption on the encrypted and write the result to the decrypted
     * @param encrypted file to apply the algorithm to
     * @param decrypted file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
    @Override
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException, IllegalKeyException {
        /* algorithm pseudo code
            for-each byte encryptedByte in encrypted
                       decryptedByte <- encryptedByte - key with underflow
                       write decryptedByte to file decrypted
        */
        super.algorithm(encrypted, decrypted, key);

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
