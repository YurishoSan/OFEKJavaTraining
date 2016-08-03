import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

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
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException {
        /* algorithm pseudo code
            for-each byte originalByte in original
                    encryptedByte <- originalByte MWO key
                    write encryptedByte to file encrypted
        */

        int c;

        while ((c = original.read()) != -1) {
            int value = MathUtils.MWO((char)c, key);
            encrypted.write(value & (0xff));
        }
        encrypted.close();
    }
}
