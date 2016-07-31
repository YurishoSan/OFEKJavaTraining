import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Encryption/Decryption function's algorithm Decorator pattern interface
 */
public interface EncryptionAlgorithm {
    void algorithm(FileInputStream inputFile, FileOutputStream outputFile, byte key) throws IOException;
}
