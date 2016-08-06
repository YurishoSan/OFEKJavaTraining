package encryption.exception;

/**
 * Created by yurisho on 05/08/2016.
 */
public class EndEventCalledBeforeStartEventException extends EncryptionException {
    public EndEventCalledBeforeStartEventException() {
        super();
    }

    public EndEventCalledBeforeStartEventException(String message) {
        super(message);
    }
}
