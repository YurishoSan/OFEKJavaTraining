package encryption.algorithms;

import encryption.design.decorator.EncryptionAlgorithm;
import encryption.design.decorator.EncryptionAlgorithmDecorator;
import encryption.EncryptionFunction;
import encryption.exception.DecryptionKeyNotFoundException;
import encryption.exception.IllegalKeyException;
import utils.MathUtils;

import java.io.*;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms Multiplication decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class MultiplicationDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {
    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public MultiplicationDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedDecryptionAlgorithm) {
        super(decoratedDecryptionAlgorithm);
    }

    /**
     * apply multiplication algorithm for decryption on the encrypted and write the result to the decrypted
     * @param encrypted file to apply the algorithm to
     * @param decrypted file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
    @Override
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException, IllegalKeyException {
        /* algorithm pseudo code
            decryptionKey <- FindDecryptionKey(key)

            for-each byte encryptedByte in encrypted
                    decryptedByte <- encryptedByte MWO decryptionKey
                    write decryptedByte to file decrypted
        */

        super.algorithm(encrypted, decrypted, key);

        if (key % 2 ==0 || key == 0) //key could cause loss of data
            throw new IllegalKeyException("in multiplication algorithm, key must not be divisible by 2, or 0");

        char decryptionKey;

        try {
            decryptionKey = FindDecryptionKey(key);

            int c;

            while ((c = encrypted.read()) != -1) {
                int value = MathUtils.MWO((char)c,decryptionKey);
                decrypted.write(value & (0xff));
            }
            decrypted.close();
        }
        catch (DecryptionKeyNotFoundException exp) {
            System.out.println("could not find a decryption key matching this key");
        }
    }

    /**
     * find the decryption key coresponding to the key.
     * the decryption key is any number in range 0-255 that satisfies the condition that:
     * decryption_key MWO key = 1
     * @param key key to find corresponding decryption key
     * @return  the decryption key
     * @throws DecryptionKeyNotFoundException if no such decryption key is found in the 0-255 range
     */
    public static char FindDecryptionKey(char key) throws DecryptionKeyNotFoundException {
        /* FindDecryptionKey pseudo code
            for (i=0..Byte Max Value)
                if (i MWO key = 1)
                    return i
         */

        for(char i = 0; i <= EncryptionFunction.BYTE_MAX_VALUE; i++) {
            if (MathUtils.MWO(i, key) == 1)
                return i;
        }

        throw new DecryptionKeyNotFoundException();
    }
}
