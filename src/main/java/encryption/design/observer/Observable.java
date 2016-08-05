package encryption.design.observer;


/**
 * and Observable object
 *
 * See: http://www.journaldev.com/1739/observer-design-pattern-in-java
 */
public interface Observable {

    //methods to register and unregister observers
    void register(Observer obj, EventTypesEnum eventType);
    void unregister(Observer obj, EventTypesEnum eventType);

    //method to notify observers of change
    void notifyObservers(EventTypesEnum eventType);
}