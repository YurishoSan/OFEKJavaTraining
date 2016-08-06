package encryption;

import encryption.design.observer.EventTypesEnum;
import encryption.design.observer.Observable;
import encryption.exception.EndEventCalledBeforeStartEventException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by yurisho on 05/08/2016.
 *
 * Test encryption.EncryptionEventListener class
 */
public class EncryptionEventListenerTest {
    private EncryptionEventListener encryptionEventListener;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Mock
    private Observable observable;

    @Mock
    private Clock clock;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Before
    public void setUp() throws EndEventCalledBeforeStartEventException {
        encryptionEventListener = new EncryptionEventListener(clock);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                encryptionEventListener.update(EventTypesEnum.FUNCTION_START);
                return null;
            }
        }).when(observable).notifyObservers(EventTypesEnum.FUNCTION_START);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                encryptionEventListener.update(EventTypesEnum.FUNCTION_END);
                return null;
            }
        }).when(observable).notifyObservers(EventTypesEnum.FUNCTION_END);

    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @After
    public void tearDown() {
        encryptionEventListener = null;
    }

    @Test
    public void eventsShouldWriteToSceneThatAlgorithmTook10Milliseconds() throws EndEventCalledBeforeStartEventException {
        Instant now = Instant.now();
        when(clock.instant()).thenReturn(now).thenReturn(now.plus(10, ChronoUnit.MILLIS));

        observable.notifyObservers(EventTypesEnum.FUNCTION_START);
        observable.notifyObservers(EventTypesEnum.FUNCTION_END);
        assertThat(outContent.toString(), is("Starting encryption/decryption function\r\n" +
                "Finished encryption/decryption function - time: 10\r\n"));
    }

}