import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

/**
 * Created by yurisho on 02/08/2016.
 *
 * Preforms Multiplication decryption algorithm
 *
 * @author Yitzhak Goldstein
 * @version 1.0
 */
public class MultiplicationDecryptionAlgorithmDecorator extends EncryptionAlgorithmDecorator {
    public MultiplicationDecryptionAlgorithmDecorator(EncryptionAlgorithm decoratedEncryptionAlgorithm) {
        super(decoratedEncryptionAlgorithm);
    }

    @Override
    public void algorithm(FileReader original, FileWriter encrypted, char key) throws IOException {
        /* algorithm pseudo code
            decryptionKey <- FindDecryptionKey(key)

            for-each byte encryptedByte in encrypted
                    decryptedByte <- encryptedByte MWO decryptionKey
                    write decryptedByte to file decrypted
        */

        throw new NotImplementedException();
    }

    public static char FindDecryptionKey(char key) {
        /* FindDecryptionKey pseudo code
            for (i=0..Byte Max Value)
                if (i MWO key = 1)
                    return i
         */

        throw new NotImplementedException();
    }
}
