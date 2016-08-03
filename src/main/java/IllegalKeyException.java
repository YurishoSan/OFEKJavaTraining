/**
 * Created by yurisho on 03/08/2016.
 *
 * exception in case the key is illegal
 */
public class IllegalKeyException extends EncryptionException {
    public IllegalKeyException() {
        super();
    }

    public IllegalKeyException(String message) {
        super(message);
    }
}
