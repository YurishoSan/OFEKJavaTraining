import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms XOR encryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class XorEncryptionAlgorithmDecorator  extends EncryptionAlgorithmDecorator {
    public XorEncryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException {
        /* algorithm pseudo code
            for-each byte originalByte in original
                    encryptedByte <- originalByte ^ key
                    write encryptedByte to file encrypted
         */

        int c;

        while ((c = original.read()) != -1) {
            int value = c ^ key;
            encrypted.write(value & (0xff));
        }
        encrypted.close();
    }
}
