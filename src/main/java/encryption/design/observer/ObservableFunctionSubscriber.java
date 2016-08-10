package encryption.design.observer;

import encryption.exception.EndEventCalledBeforeStartEventException;

/**
 * a class implementing the Observer contract
 *
 * See: http://www.journaldev.com/1739/observer-design-pattern-in-java
 */
public abstract class ObservableFunctionSubscriber implements Observer, Cloneable {
    private Observable observable;

    @Override
    public ObservableFunctionSubscriber clone() throws CloneNotSupportedException {
        try {
            ObservableFunctionSubscriber result = (ObservableFunctionSubscriber) super.clone();
            result.setObservable(observable.clone());
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); //can't happen
        }
    }

    public final void update(EventTypesEnum eventType) throws EndEventCalledBeforeStartEventException {
        switch(eventType) {
            case FUNCTION_START:
                startEvent();
                break;
            case FUNCTION_END:
                endEvent();
                break;
            default:
                throw new IllegalArgumentException("The event type can not be observed");
        }
    }

    public final void setObservable(Observable value) {
        observable = value;
    }
    public final Observable getObservable() { return observable; }

    public abstract void startEvent();
    public abstract void endEvent() throws EndEventCalledBeforeStartEventException;
}
