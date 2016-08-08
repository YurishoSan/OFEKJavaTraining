package encryption.algorithms;

import encryption.exception.EncryptionException;
import encryption.exception.IllegalKeyException;
import javafx.util.Pair;
import utils.MathUtils;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms Multiplication encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class MultiplicationEncryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {
    private char key;

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public MultiplicationEncryptionAlgorithmDecorator(BasicAlgorithm decoratedEncryptionAlgorithm, char key) {
        super(decoratedEncryptionAlgorithm);
        this.key = key;
        this.addStep(new Pair<>((c) -> {
            /* algorithm pseudo code
                 encryptedByte <- originalByte MWO key
            */
            return (int) MathUtils.MWO((char)(int)c.getValue(), c.getKey());
        },key));
    }

    public void init() throws EncryptionException {
        super.init();

        if (key % 2 ==0 || key == 0) //key could cause loss of data
            throw new IllegalKeyException("in multiplication algorithm, key must not be divisible by 2, or 0");
    }
}
