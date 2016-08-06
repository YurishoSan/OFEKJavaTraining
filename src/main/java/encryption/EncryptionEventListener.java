package encryption;

import encryption.design.observer.ObservableFunctionSubscriber;
import encryption.exception.EndEventCalledBeforeStartEventException;

import java.time.Clock;

/**
 * Created by yurisho on 05/08/2016.
 *
 * listener for startEvent and endEvent that records the time passed between the two
 *
 */
public class EncryptionEventListener extends ObservableFunctionSubscriber {
    private long startTime = 0;
    private Clock clock;

    public EncryptionEventListener(Clock clock) {
        this.clock = clock;
    }

    public void startEvent() {
        /*
        startEvent pseudo code
            print("Starting encryption/decryption function")
            start counting the time
         */
        System.out.println("Starting encryption/decryption function");
        this.startTime = clock.instant().toEpochMilli();
    }

    public void endEvent() throws EndEventCalledBeforeStartEventException {
        /*
        endEvent pseudo code
            end counting the time
            print("Finished encryption/decryption function - time: <time it took the algorithm to run>"
         */
        if (startTime == 0) // startEvent was neverCalled
            throw new EndEventCalledBeforeStartEventException();

        long estimatedTime = clock.instant().toEpochMilli() - startTime;

        System.out.println("Finished encryption/decryption function - time: " + estimatedTime);
    }
}
