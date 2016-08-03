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
    public void algorithm(FileReader encrypted, FileWriter decrypted, char key) throws IOException {
        /* algorithm pseudo code
            decryptionKey <- FindDecryptionKey(key)

            for-each byte encryptedByte in encrypted
                    decryptedByte <- encryptedByte MWO decryptionKey
                    write decryptedByte to file decrypted
        */
        char decryptionKey;

        try {
            decryptionKey = FindDecryptionKey(key);

            int c;

            while ((c = encrypted.read()) != -1) {
                int value = MathUtils.MWO((char)c,decryptionKey);
                decrypted.write(value & (0xff));
            }
            decrypted.close();
        }
        catch (DecryptionKeyNotFoundException exp) {
            System.out.println("could not find a decryption key matching this key");
        }
    }

    public static char FindDecryptionKey(char key) throws DecryptionKeyNotFoundException {
        /* FindDecryptionKey pseudo code
            for (i=0..Byte Max Value)
                if (i MWO key = 1)
                    return i
         */

        for(char i=0; i <= EncryptionFunction.BYTE_MAX_VALUE; i++) {
            if (MathUtils.MWO(i, key) == 1)
                return i;
        }

        throw new DecryptionKeyNotFoundException();
    }
}
