package encryption.algorithms;

import encryption.design.decorator.EncryptionAlgorithm;
import encryption.exception.IllegalKeyException;

import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.2
 */
public class NoneEncryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public NoneEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    /**
     * apply no algorithm for encryption on the original and write the result to the encrypted
     * @param original file to apply the algorithm to
     * @param encrypted file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
    @Override
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException, IllegalKeyException {
        /* algorithm pseudo code
            copy original file to encrypted file
         */
        super.algorithm(original, encrypted, key);
        int c;

        while ((c = original.read()) != -1) {
            encrypted.write(c & (0xff));
        }
        encrypted.close();
    }
}
