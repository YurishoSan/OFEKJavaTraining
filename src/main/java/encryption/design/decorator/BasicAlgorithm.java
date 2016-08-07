package encryption.design.decorator;

import encryption.EncryptionFunction;
import encryption.exception.IllegalKeyException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yurisho on 06/08/2016.
 */
public class BasicAlgorithm implements EncryptionAlgorithm {
    /**
     * base algorithm function - check key is in the 0-255 range
     * @param inputFile file to apply the algorithm to
     * @param outputFile file to write the result into
     * @param key key to use in the algorithm
     * @throws IllegalKeyException if key is not in the 0-255 range
     */
    public void algorithm(FileReader inputFile, FileWriter outputFile, char key) throws IllegalKeyException {
        if (key > EncryptionFunction.BYTE_MAX_VALUE)
            throw new IllegalKeyException();
    }
}
