import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms Multiplication encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class MultiplicationEncryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {
    public MultiplicationEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileInputStream original, FileOutputStream encrypted, byte key) throws IOException {
        /* algorithm pseudo code
            for-each byte originalByte in original
                    encryptedByte <- originalByte MWO key
                    write encryptedByte to file encrypted
        */
    }
}
