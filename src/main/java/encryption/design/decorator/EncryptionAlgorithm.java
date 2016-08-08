package encryption.design.decorator;

import encryption.exception.EncryptionException;
import javafx.util.Pair;

import java.io.*;
import java.util.List;
import java.util.function.Function;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Encryption/Decryption function's algorithm Decorator pattern interface
 *
 * @author Yitzhak Goldstein
 * @version 2.0
 */
public interface EncryptionAlgorithm {
    /**
     * prepare for running the algorithm. must be ran once before algorithm() or step()
     */
    void init() throws EncryptionException;

    /**
     * apply the algorithm on the inputFile and write the result to the outputFile
     * @throws IOException if could not handle the files
     */
    void algorithm() throws IOException;

    void setInputFile(File value);
    void setOutputFile(File value);

    File getInputFile();
    File getOutputFile();

    /*
     step is a pair of function and encryption keys. each function gets the key and a value, and returns that value encrypted by that key.
     */
    void addStep(Pair<Function<Pair<Character, Integer>, Integer>, Character> value);
    Pair<Function<Pair<Character, Integer>, Integer>, Character> getStep(int index);
    List<Pair<Function<Pair<Character, Integer>, Integer>, Character>> getSteps();
}
