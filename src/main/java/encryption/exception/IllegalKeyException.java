package encryption.exception;

/**
 * Created by yurisho on 03/08/2016.
 *
 * encryption.exception in case the key is illegal
 */
public class IllegalKeyException extends EncryptionException {
    public IllegalKeyException() {
        super();
    }

    public IllegalKeyException(String message) {
        super(message);
    }
}
