package encryption.algorithms;

import exception.IllegalKeyException;

import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class NoneDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator{

    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public NoneDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    /**
     * apply no algorithm for decryption on the encrypted and write the result to the decrypted
     * @param encrypted file to apply the algorithm to
     * @param decrypted file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
    @Override
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException, IllegalKeyException {
        /* algorithm pseudo code
            copy encrypted file to decrypted file
         */
        super.algorithm(encrypted, decrypted, key);
        int c;

        while ((c = encrypted.read()) != -1) {
            decrypted.write(c & (0xff));
        }
        decrypted.close();
    }
}
