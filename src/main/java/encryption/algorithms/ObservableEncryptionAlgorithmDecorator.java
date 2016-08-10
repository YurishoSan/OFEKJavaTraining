package encryption.algorithms;

import encryption.design.decorator.EncryptionAlgorithm;
import encryption.design.observer.ObservableFunction;
import encryption.exception.EncryptionException;
import javafx.util.Pair;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by yurisho on 05/08/2016.
 *
 * Decorator for encryption.design.decorator.EncryptionAlgorithm interface,
 * also observable using encryption.design.observer.ObservableFunctionSubscriber
 *
 * @author Yitzhak Goldstein
 * @version 3.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class ObservableEncryptionAlgorithmDecorator extends ObservableFunction implements EncryptionAlgorithm, Cloneable {
    protected EncryptionAlgorithm decoratedEncryptionAlgorithm;

    public ObservableEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        setDecoratedEncryptionAlgorithm(decoratedEncryptionAlgorithm);
    }

    @Override
    public ObservableEncryptionAlgorithmDecorator clone() throws CloneNotSupportedException {
        try {
            ObservableEncryptionAlgorithmDecorator result = (ObservableEncryptionAlgorithmDecorator) super.clone();
            result.setDecoratedEncryptionAlgorithm(decoratedEncryptionAlgorithm.clone());
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    @Override
    public void init() throws EncryptionException {
        if (decoratedEncryptionAlgorithm != null)
            decoratedEncryptionAlgorithm.init();
    }

    @Override
    public void algorithm() throws IOException {
        if (decoratedEncryptionAlgorithm != null)
            decoratedEncryptionAlgorithm.algorithm();
    }

    @Override
    public void setInputFile(File value) {
        if (decoratedEncryptionAlgorithm != null)
            decoratedEncryptionAlgorithm.setInputFile(value);
    }

    @Override
    public void setOutputFile(File value) {
        if (decoratedEncryptionAlgorithm != null)
            decoratedEncryptionAlgorithm.setOutputFile(value);
    }

    @Override
    public void addStep(Pair<Function<Pair<Character, Integer>, Integer>, Character> value) {
        if (decoratedEncryptionAlgorithm != null)
            decoratedEncryptionAlgorithm.addStep(value);
    }

    @Override
    public File getInputFile() {
        return decoratedEncryptionAlgorithm.getInputFile();
    }

    @Override
    public File getOutputFile() {
        return decoratedEncryptionAlgorithm.getOutputFile();
    }

    @Override
    public Pair<Function<Pair<Character, Integer>, Integer>, Character> getStep(int index) {
        return decoratedEncryptionAlgorithm.getStep(index);
    }

    @Override
    public List<Pair<Function<Pair<Character, Integer>, Integer>, Character>> getSteps() {
        return decoratedEncryptionAlgorithm.getSteps();
    }
}
