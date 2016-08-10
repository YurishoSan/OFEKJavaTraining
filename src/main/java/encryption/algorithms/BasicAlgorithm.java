package encryption.algorithms;

import encryption.EncryptionFunction;
import encryption.design.decorator.EncryptionAlgorithm;
import encryption.exception.EncryptionException;
import encryption.exception.IllegalKeyException;
import javafx.util.Pair;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * Created by yurisho on 06/08/2016.
 *
 * basic algorithm all others decorate
 *
 * @version 2.1
 */
@Data
public class BasicAlgorithm implements EncryptionAlgorithm, Cloneable {
    /**
     * file to apply the algorithm to
     */
    private File inputFile;
    /**
     * file to write the result into
     */
    private File outputFile;
    /**
     * keys to use in the algorithm
     */
    private char key;

    /**
     * function to run at each step of the algorithm
     */
    private List<Pair<Function<Pair<Character, Integer>, Integer>, Character>> steps;

    public BasicAlgorithm() {
        steps = new ArrayList<>();
        addStep(new Pair<>(val -> (val.getValue()), (char)0));
    }

    @Override
    public BasicAlgorithm clone() throws CloneNotSupportedException {
        try {
            BasicAlgorithm result = (BasicAlgorithm) super.clone();
            List<Pair<Function<Pair<Character, Integer>, Integer>, Character>> steps = new ArrayList<>();
            steps.addAll(this.steps); // the actual steps are unmutable so this is fine
            result.setSteps(steps);
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    /**
     * base init function - check key is in the 0-255 range
     * @throws IllegalKeyException if key is not in the 0-255 range
     */
    public void init() throws EncryptionException{
        /*if (keys.get(0) > EncryptionFunction.BYTE_MAX_VALUE)
            throw new IllegalKeyException();*/
    }

    public void algorithm() throws IOException{
        FileReader inputReader = new FileReader(getInputFile());
        FileWriter outputWriter = new FileWriter(getOutputFile());

        int c;

        while ((c = inputReader.read()) != -1) {
            for (Pair<Function<Pair<Character, Integer>, Integer>, Character> step : steps) {
                c = step.getKey().apply(new Pair<>(step.getValue(), c));
            }
            outputWriter.write(c & (0xff));
        }
        outputWriter.close();
    }

    @Override
    public void addStep(Pair<Function<Pair<Character, Integer>, Integer>, Character> value) {
        steps.add(value);
    }

    @Override
    public Pair<Function<Pair<Character, Integer>, Integer>, Character> getStep(int index) {
        return steps.get(index);
    }
}
