package encryption.algorithms;

import encryption.design.decorator.EncryptionAlgorithm;
import encryption.exception.IllegalKeyException;
import utils.MathUtils;

import java.io.*;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms Multiplication encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class MultiplicationEncryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public MultiplicationEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    /**
     * apply multiplication algorithm for encryption on the original and write the result to the encrypted
     * @param original file to apply the algorithm to
     * @param encrypted file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
    @Override
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException, IllegalKeyException {
        /* algorithm pseudo code
            for-each byte originalByte in original
                    encryptedByte <- originalByte MWO key
                    write encryptedByte to file encrypted
        */
        super.algorithm(original, encrypted, key);

        if (key % 2 ==0 || key == 0) //key could cause loss of data
            throw new IllegalKeyException("in multiplication algorithm, key must not be divisible by 2, or 0");

        int c;

        while ((c = original.read()) != -1) {
            int value = MathUtils.MWO((char)c, key);
            encrypted.write(value & (0xff));
        }
        encrypted.close();
    }
}
