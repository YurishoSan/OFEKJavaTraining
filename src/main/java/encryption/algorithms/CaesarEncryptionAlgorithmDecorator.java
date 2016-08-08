package encryption.algorithms;

import encryption.EncryptionFunction;
import encryption.exception.EncryptionException;
import javafx.util.Pair;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms caesar encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class CaesarEncryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public CaesarEncryptionAlgorithmDecorator(BasicAlgorithm decoratedEncryptionAlgorithm, char key) {
        super(decoratedEncryptionAlgorithm);
        this.addStep(new Pair<>((c) -> {
            /* step pseudo code
                encryptedByte <- originalByte + key with overflow
            */
            int value = c.getValue() + c.getKey();
            while (value > EncryptionFunction.BYTE_MAX_VALUE)
                value = value - EncryptionFunction.BYTE_MAX_VALUE - 1;
            return value;
        }, key));
    }

    public void init() throws EncryptionException {
        super.init();
    }
}
