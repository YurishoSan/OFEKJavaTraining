import java.io.*;

/**
 * Created by yurisho on 31/07/2016.
 *
 * Preforms no encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.1
 */
public class NoneEncryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {

    public NoneEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException {
        /* algorithm pseudo code
            copy original file to encrypted file
         */
        super.algorithm(original, encrypted, key);
        int c;

        while ((c = original.read()) != -1) {
            encrypted.write(c & (0xff));
        }
        encrypted.close();
    }
}
