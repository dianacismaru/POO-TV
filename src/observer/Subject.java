package observer;

public interface Subject {
    /**
     * Add an oberver to the list of observers of the subject
     * @param observer the observer to be added
     */
    void addObserver(Observer observer);

    /**
     * Send a specific notification to all the observers of the subject
     * @param notification the notification to be broadcast to observers
     */
    void notifyObservers(Notification notification);
}
