package encryption.design.observer;

/**
 * and Observer object
 *
 * See: http://www.journaldev.com/1739/observer-design-pattern-in-java
 */
public interface Observer {

    //method to update the observer, used by subject
    void update(EventTypesEnum eventType);

    //attach with subject to observe
    void setObservable(Observable sub);
    Observable getObservable();
}
