package encryption.algorithms;

import javafx.util.Pair;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 3.0
 */
public class NoneEncryptionAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public NoneEncryptionAlgorithmDecorator(BasicAlgorithm decoratedEncryptionAlgorithm, char key) {
        super(decoratedEncryptionAlgorithm);
        /* algorithm pseudo code
            copy original file to encrypted file
        */
        this.addStep(new Pair<>(Pair::getValue, key));
    }
}
