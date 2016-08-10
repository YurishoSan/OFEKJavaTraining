package encryption.design.observer;

import encryption.exception.EndEventCalledBeforeStartEventException;

/**
 * and Observer object
 *
 * See: http://www.journaldev.com/1739/observer-design-pattern-in-java
 */
public interface Observer {

    Observer clone() throws CloneNotSupportedException;

    //method to update the observer, used by subject
    void update(EventTypesEnum eventType) throws EndEventCalledBeforeStartEventException;

    //attach with subject to observe
    void setObservable(Observable sub);
    Observable getObservable();
}
