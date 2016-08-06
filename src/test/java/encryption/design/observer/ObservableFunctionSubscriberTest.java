package encryption.design.observer;

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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by yurisho on 05/08/2016.
 *
 * Test encryption.design.observer.ObservableFunctionSubscriberTest class
 */
public class ObservableFunctionSubscriberTest {
    private boolean eventStartCalled;
    private boolean eventEndCalled;
    private ObservableFunctionSubscriber observableFunctionSubscriber;
    @Mock
    private Observable observable;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws EndEventCalledBeforeStartEventException {
        eventStartCalled = false;
        eventEndCalled = false;
        observableFunctionSubscriber = new ObservableFunctionSubscriber() {
            @Override
            public void startEvent() {
                eventStartCalled = true;
            }

            @Override
            public void endEvent() {
                eventEndCalled = true;
            }
        };

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                observableFunctionSubscriber.update(EventTypesEnum.FUNCTION_END);
                return null;
            }
        }).when(observable).notifyObservers(EventTypesEnum.FUNCTION_END);

        //observableFunctionSubscriber.setObservable(observable);
    }

    @After
    public void tearDown() {
        observableFunctionSubscriber = null;
    }

    @Test
    public void WhenObservableNotifiesTheObserverBeingRegisteredForFunctionStartUpdateShouldRunEventStart() throws EndEventCalledBeforeStartEventException {
        observable.register(observableFunctionSubscriber, EventTypesEnum.FUNCTION_START);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                observableFunctionSubscriber.update(EventTypesEnum.FUNCTION_START);
                return null;
            }
        }).when(observable).notifyObservers(EventTypesEnum.FUNCTION_START);

        observable.notifyObservers(EventTypesEnum.FUNCTION_START);

        assertThat(eventStartCalled, is(true));
    }

    @Test
    public void WhenObservableNotifiesTheObserverBeingRegisteredForFunctionEndUpdateShouldRunEventEnd() throws EndEventCalledBeforeStartEventException {
        observable.register(observableFunctionSubscriber, EventTypesEnum.FUNCTION_END);

        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                observableFunctionSubscriber.update(EventTypesEnum.FUNCTION_END);
                return null;
            }
        }).when(observable).notifyObservers(EventTypesEnum.FUNCTION_END);

        observable.notifyObservers(EventTypesEnum.FUNCTION_END);

        assertThat(eventEndCalled, is(true));
    }

}