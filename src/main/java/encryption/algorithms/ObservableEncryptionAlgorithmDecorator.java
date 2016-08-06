package encryption.algorithms;

import encryption.design.decorator.EncryptionAlgorithm;
import encryption.design.observer.EventTypesEnum;
import encryption.design.observer.ObservableFunction;
import encryption.exception.EndEventCalledBeforeStartEventException;
import encryption.exception.IllegalKeyException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yurisho on 05/08/2016.
 *
 * Decorator for encryption.design.decorator.EncryptionAlgorithm interface,
 * also observable using encryption.design.observer.ObservableFunctionSubscriber
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public abstract class ObservableEncryptionAlgorithmDecorator extends ObservableFunction implements EncryptionAlgorithm {
    protected EncryptionAlgorithm decoratedEncryptionAlgorithm;

    public ObservableEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        this.decoratedEncryptionAlgorithm = decoratedEncryptionAlgorithm;
    }

    public final void callAlgorithm(FileReader original, FileWriter encrypted, char key) throws IOException, IllegalKeyException, EndEventCalledBeforeStartEventException {
        notifyObservers(EventTypesEnum.FUNCTION_START);
        decoratedEncryptionAlgorithm.algorithm(original, encrypted, key);
        notifyObservers(EventTypesEnum.FUNCTION_END);
    }

    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException, IllegalKeyException {
        decoratedEncryptionAlgorithm.algorithm(original, encrypted, key);
    }
}
