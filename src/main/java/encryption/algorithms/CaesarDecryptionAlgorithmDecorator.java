package encryption.algorithms;

import encryption.EncryptionFunction;
import encryption.exception.EncryptionException;
import javafx.util.Pair;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms caesar decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class CaesarDecryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {
    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public CaesarDecryptionAlgorithmDecorator(BasicAlgorithm decoratedDecryptionAlgorithm, char key) {
        super(decoratedDecryptionAlgorithm);
        this.addStep(new Pair<>((c) -> {
            /* algorithm pseudo code
                   decryptedByte <- encryptedByte - key with underflow
            */
            int value = c.getValue() - c.getKey();
            while (value < 0)
                value = EncryptionFunction.BYTE_MAX_VALUE + value + 1;
            return value;
        }, key));
    }

    public void init() throws EncryptionException{
        super.init();
    }
}
