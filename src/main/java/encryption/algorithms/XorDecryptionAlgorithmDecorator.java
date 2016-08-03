package encryption.algorithms;

import exception.IllegalKeyException;

import java.io.*;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms XOR decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class XorDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator{

    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public XorDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    /**
     * apply XOR algorithm for decryption on the encrypted and write the result to the decrypted
     * @param encrypted file to apply the algorithm to
     * @param decrypted file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
    @Override
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException, IllegalKeyException {
         /* algorithm pseudo code
            for-each byte encryptedByte in encrypted
                       decryptedByte <- encryptedByte ^ key
                       write decryptedByte to file decrypted
        */
        super.algorithm(encrypted, decrypted, key);

        int c;

        while ((c = encrypted.read()) != -1) {
            int value = c ^ key;
            decrypted.write(value & (0xff));
        }
        decrypted.close();
    }
}
