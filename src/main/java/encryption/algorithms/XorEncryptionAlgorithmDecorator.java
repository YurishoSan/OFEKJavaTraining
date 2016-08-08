package encryption.algorithms;

import javafx.util.Pair;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms XOR encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class XorEncryptionAlgorithmDecorator  extends ObservableEncryptionAlgorithmDecorator {

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public XorEncryptionAlgorithmDecorator(BasicAlgorithm decoratedEncryptionAlgorithm, char key) {
        super(decoratedEncryptionAlgorithm);
        this.addStep(new Pair<>((c) -> {
            /* step pseudo code
                decryptedByte <- encryptedByte ^ key
            */
            return c.getValue() ^ c.getKey();
        }, key));
    }
}
