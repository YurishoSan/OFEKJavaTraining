package encryption.algorithms;

import encryption.EncryptionFunction;
import encryption.exception.DecryptionKeyNotFoundException;
import encryption.exception.EncryptionException;
import encryption.exception.IllegalKeyException;
import javafx.util.Pair;
import utils.MathUtils;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms Multiplication decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class MultiplicationDecryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {
    private  char key;
    private char decryptionKey;

    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public MultiplicationDecryptionAlgorithmDecorator(BasicAlgorithm decoratedDecryptionAlgorithm, char key) {
        super(decoratedDecryptionAlgorithm);
        this.key = key;
        this.addStep(new Pair<>((c) -> {
            /* algorithm pseudo code
                decryptedByte <- encryptedByte MWO decryptionKey
            */
            return (int) MathUtils.MWO((char)(int)c.getValue(), decryptionKey);
        }, key));
    }

    public void init() throws EncryptionException{
        super.init();

        if (key % 2 ==0 || key == 0) //key could cause loss of data
            throw new IllegalKeyException("in multiplication algorithm, key must not be divisible by 2, or 0");

        try {
            decryptionKey = FindDecryptionKey(key);
        } catch (DecryptionKeyNotFoundException exp) {
            System.out.println("could not find a decryption key matching this key");
        }
    }

    /**
     * find the decryption key coresponding to the key.
     * the decryption key is any number in range 0-255 that satisfies the condition that:
     * decryption_key MWO key = 1
     * @param key key to find corresponding decryption key
     * @return  the decryption key. uses only the first element in the list
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
