import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Decorator for EncryptionAlgorithm interface
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public abstract class EncryptionAlgorithmDecorator implements EncryptionAlgorithm {
    protected EncryptionAlgorithm decoratedEncryptionAlgorithm;

    public EncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        this.decoratedEncryptionAlgorithm = decoratedEncryptionAlgorithm;
    }

    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException, IllegalKeyException {
        decoratedEncryptionAlgorithm.algorithm(original, encrypted, key);
    }
}
