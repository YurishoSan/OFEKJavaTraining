package encryption.design.observer;

import encryption.exception.EndEventCalledBeforeStartEventException;
import lombok.Data;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * a class implementing the Observable contract
 *
 * See: http://www.journaldev.com/1739/observer-design-pattern-in-java
 */
@Data
public class ObservableFunction implements Observable, Cloneable {
    private List<Observer> observersStart;
    private List<Observer> observersEnd;

    private final Object MUTEX= new Object();

    public ObservableFunction() {
        observersStart = new ArrayList<Observer>();
        observersEnd = new ArrayList<Observer>();
    }

    @Override
    public ObservableFunction clone() throws CloneNotSupportedException {
        try {
            ObservableFunction result = (ObservableFunction) super.clone();

            ArrayList<Observer> observersStart = new ArrayList<>();
            for (Observer observer : this.observersStart)
                observersStart.add(observer.clone());
            result.setObserversStart(observersStart);

            ArrayList<Observer> observersEnd = new ArrayList<>();
            for (Observer observer : this.observersEnd)
                observersEnd.add(observer.clone());
            result.setObserversEnd(observersEnd);

            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); //can't happen
        }
    }

    public void register(Observer obj, EventTypesEnum eventType) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            switch (eventType) {
                case FUNCTION_START:
                    if(!observersStart.contains(obj)) observersStart.add(obj);
                    obj.setObservable(this);
                    break;
                case FUNCTION_END:
                    if(!observersEnd.contains(obj)) observersEnd.add(obj);
                    obj.setObservable(this);
                    break;
                default:
                    throw new IllegalArgumentException("The event type is not observable");
            }
        }
    }

    public void unregister(Observer obj, EventTypesEnum eventType) {
        synchronized (MUTEX) {
            switch (eventType) {
                case FUNCTION_START:
                    observersStart.remove(obj);
                    obj.setObservable(null);
                    break;
                case FUNCTION_END:
                    observersEnd.remove(obj);
                    obj.setObservable(null);
                    break;
                default:
                    throw new IllegalArgumentException("The event type is not observable");
            }
        }
    }

    public void notifyObservers(EventTypesEnum eventType) throws EndEventCalledBeforeStartEventException {
        List<Observer> observersLocal;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            switch (eventType) {
                case FUNCTION_START:
                    observersLocal = new ArrayList<Observer>(this.observersStart);
                    break;
                case FUNCTION_END:
                    observersLocal = new ArrayList<Observer>(this.observersEnd);
                    break;
                default:
                    throw new IllegalArgumentException("The event type is not observable");
            }
        }

        for (Observer obj : observersLocal) {
            obj.update(eventType);
        }
    }
}
