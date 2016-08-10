package encryption.algorithms;

import encryption.EncryptionFunction;
import encryption.design.decorator.EncryptionAlgorithm;
import encryption.exception.EncryptionException;
import javafx.util.Pair;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

/**
 * Created by yurisho on 09/08/2016.
 *
 * Preforms 2 encryption algorithms one after the other
 *
 * to decrypt a file encrypted with double encryption create a new DoubleAlgorithmDecorator with the decryption algorithms, passed in reverse
 * i.e. to decrypt a file encrypted by DoubleAlgorithmDecorator(encryptAlgo1, encryptAlgo2)
 * you need to use DoubleAlgorithmDecorator(decryptAlgo2, decryptAlgo1)
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class DoubleAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {

    private EncryptionAlgorithm secondAlgorithm;

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public DoubleAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm, EncryptionAlgorithm secondAlgorithm) {
        super(decoratedEncryptionAlgorithm);
        this.secondAlgorithm = secondAlgorithm;
    }

    @Override
    public DoubleAlgorithmDecorator clone() throws CloneNotSupportedException {
        try {
            DoubleAlgorithmDecorator result = (DoubleAlgorithmDecorator) super.clone();
            result.secondAlgorithm = secondAlgorithm.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }

    public void init() throws EncryptionException{
        super.init();
        secondAlgorithm.init();
    }

    public void algorithm() throws IOException {
        /*
            algorithm pseudo code
                read input into c, and do while not finished the file
                    apply the decorated algorithm step function on c, and write result to c.
                    apply the second algorithm step function on c, and write result to c.
                    write c to output file
         */

        FileReader inputReader = new FileReader(getInputFile());
        FileWriter outputWriter = new FileWriter(getOutputFile());

        int c;

        while ((c = inputReader.read()) != -1) {
            //apply algo 0
            for (Pair<Function<Pair<Character, Integer>, Integer>, Character> step : getSteps()) {
                c = step.getKey().apply(new Pair<>(step.getValue(), c));
            }

            //apply algo 1
            for (Pair<Function<Pair<Character, Integer>, Integer>, Character> step : secondAlgorithm.getSteps()) {
                c = step.getKey().apply(new Pair<>(step.getValue(), c));
            }

            outputWriter.write(c & (0xff));
        }
        outputWriter.close();
    }
}
