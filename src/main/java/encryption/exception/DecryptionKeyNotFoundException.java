package encryption.exception;

/**
 * Created by yurisho on 03/08/2016.
 *
 * Exception when no number in range 0-255 qualifies the condition that num MWO key is 1
 */
public class DecryptionKeyNotFoundException extends EncryptionException {
    public DecryptionKeyNotFoundException() {
        super();
    }

    public DecryptionKeyNotFoundException(String message) {
        super(message);
    }
}
