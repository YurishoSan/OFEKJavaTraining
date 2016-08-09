package encryption.algorithms;

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
 * Preforms 2 encryption algorithms 1 for odd bytes and 1 for even bytes
 *
 * to decrypt a file encrypted with double encryption create a new DoubleAlgorithmDecorator with the decryption algorithms, passed in the same order
 * i.e. to decrypt a file encrypted by SplitAlgorithmDecorator(encryptAlgo1, encryptAlgo2)
 * you need to use SplitAlgorithmDecorator(decryptAlgo1, decryptAlgo2)
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class SplitAlgorithmDecorator extends ObservableEncryptionAlgorithmDecorator {
    private EncryptionAlgorithm secondAlgorithm;

    /**
     * decorator contor
     * @param decoratedEncryptionAlgorithm algorithm to decorate
     */
    public SplitAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm, EncryptionAlgorithm secondAlgorithm) {
        super(decoratedEncryptionAlgorithm);
        this.secondAlgorithm = secondAlgorithm;
    }

    public void init() throws EncryptionException {
        super.init();
        secondAlgorithm.init();
    }

    public void algorithm() throws IOException {
        /*
            algorithm pseudo code
                read input into c, and do while not finished the file
                    alternately apply the decorated algorithm step function,
                    or the second algorithm step function, and write result to c.
                    write c to output file
         */

        FileReader inputReader = new FileReader(getInputFile());
        FileWriter outputWriter = new FileWriter(getOutputFile());

        int c;
        int algo = 0;

        while ((c = inputReader.read()) != -1) {
            if (algo == 0)
                //algo 0
                for (Pair<Function<Pair<Character, Integer>, Integer>, Character> step : getSteps()) {
                    c = step.getKey().apply(new Pair<>(step.getValue(), c));
                }
            else
                //algo 1
                for (Pair<Function<Pair<Character, Integer>, Integer>, Character> step : secondAlgorithm.getSteps()) {
                    c = step.getKey().apply(new Pair<>(step.getValue(), c));
                }

            outputWriter.write(c & (0xff));
            algo = (algo + 1) % 2;
        }
        outputWriter.close();
    }
}
