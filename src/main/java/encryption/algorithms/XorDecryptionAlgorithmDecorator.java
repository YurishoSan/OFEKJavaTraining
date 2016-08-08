package encryption.algorithms;

import javafx.util.Pair;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms XOR decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class XorDecryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {

    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public XorDecryptionAlgorithmDecorator(BasicAlgorithm decoratedDecryptionAlgorithm, char key) {
        super(decoratedDecryptionAlgorithm);
        this.addStep(new Pair<>((c) -> {
            /* step pseudo code
                decryptedByte <- encryptedByte ^ key
            */
            return c.getValue() ^ c.getKey();
        }, key));
    }
}
