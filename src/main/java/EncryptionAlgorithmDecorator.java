import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Decorator for EncryptionAlgorithm interface
 */
public abstract class EncryptionAlgorithmDecorator implements EncryptionAlgorithm {
    protected EncryptionAlgorithm decoratedEncryptionAlgorithm;

    public EncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        this.decoratedEncryptionAlgorithm = decoratedEncryptionAlgorithm;
    }

    public void algorithm(FileInputStream original, FileOutputStream encrypted, byte key) throws IOException {
        decoratedEncryptionAlgorithm.algorithm(original, encrypted, key);
    }
}
