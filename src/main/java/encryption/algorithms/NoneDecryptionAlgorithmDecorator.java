package encryption.algorithms;

import javafx.util.Pair;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class NoneDecryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {

    /**
     * decorator contor
     * @param decoratedDecryptionAlgorithm algorithm to decorate
     */
    public NoneDecryptionAlgorithmDecorator(BasicAlgorithm decoratedDecryptionAlgorithm, char key) {
        super(decoratedDecryptionAlgorithm);
        /* algorithm pseudo code
            copy original file to encrypted file
        */
        this.addStep(new Pair<>(Pair::getValue, key));
    }


}
