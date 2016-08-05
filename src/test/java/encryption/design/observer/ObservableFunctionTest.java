package encryption.design.observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by yurisho on 03/08/2016.
 *
 * Test encryption.design.observer.ObservableFunctionTest class
 */
public class ObservableFunctionTest {
    private ObservableFunction observableFunction;

    @Before
    public void setUp() {
        observableFunction = new ObservableFunction();
    }

    @After
    public void tearDown() {
        observableFunction = null;
    }

    @Test
    public void registerToFunctionStartShouldAddTheObserverToObserversStart() {
        Observer observer = Mockito.mock(Observer.class);
        observableFunction.register(observer, EventTypesEnum.FUNCTION_START);
        assertThat(observableFunction.getObserversStart().contains(observer), is(true));
        verify(observer, times(1)).setObservable(observableFunction);
    }

    @Test
    public void registerToFunctionEndShouldAddTheObserverToObserversEnd() {
        Observer observer = Mockito.mock(Observer.class);
        observableFunction.register(observer, EventTypesEnum.FUNCTION_END);
        assertThat(observableFunction.getObserversEnd().contains(observer), is(true));
        verify(observer, times(1)).setObservable(observableFunction);
    }

    @Test
    public void unregisterToFunctionStartShouldAddTheObserverToObserversStart() {
        Observer observer = Mockito.mock(Observer.class);
        observableFunction.register(observer, EventTypesEnum.FUNCTION_START);
        observableFunction.unregister(observer, EventTypesEnum.FUNCTION_START);
        assertThat(observableFunction.getObserversStart().contains(observer), is(false));
        verify(observer, times(1)).setObservable(null);
    }

    @Test
    public void unregisterToFunctionEndShouldAddTheObserverToObserversStart() {
        Observer observer = Mockito.mock(Observer.class);
        observableFunction.register(observer, EventTypesEnum.FUNCTION_END);
        observableFunction.unregister(observer, EventTypesEnum.FUNCTION_END);
        assertThat(observableFunction.getObserversEnd().contains(observer), is(false));
        verify(observer, times(1)).setObservable(null);
    }

    @Test
    public void notifyObserversToFunctionStartShouldTriggerTheObserverUpdateFunction() {
        Observer observer = Mockito.mock(Observer.class);
        observableFunction.register(observer, EventTypesEnum.FUNCTION_START);
        observableFunction.notifyObservers(EventTypesEnum.FUNCTION_START);
        verify(observer, times(1)).update(EventTypesEnum.FUNCTION_START);
    }

    @Test
    public void notifyObserversToFunctionEndShouldTriggerTheObserverUpdateFunction() {
        Observer observer = Mockito.mock(Observer.class);
        observableFunction.register(observer, EventTypesEnum.FUNCTION_END);
        observableFunction.notifyObservers(EventTypesEnum.FUNCTION_END);
        verify(observer, times(1)).update(EventTypesEnum.FUNCTION_END);
    }
}