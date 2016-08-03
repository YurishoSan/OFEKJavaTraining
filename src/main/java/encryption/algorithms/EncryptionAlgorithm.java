package encryption.algorithms;

import exception.IllegalKeyException;

import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Encryption/Decryption function's algorithm Decorator pattern interface
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public interface EncryptionAlgorithm {
    /**
     * apply the algorithm on the inputFile and write the result to the outputFile
     * @param inputFile file to apply the algorithm to
     * @param outputFile file to write the result into
     * @param key key to use in the algorithm
     * @throws IOException if could not handle the files
     */
    void algorithm(FileReader inputFile, FileWriter outputFile, char key) throws IOException, IllegalKeyException;
}
