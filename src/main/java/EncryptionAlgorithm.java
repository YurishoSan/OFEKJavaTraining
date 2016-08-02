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
    void algorithm(FileReader inputFile, FileWriter outputFile, char key) throws IOException;
}
