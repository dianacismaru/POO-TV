package observer;

public interface Observer {
    /**
     * Update the observer with the given notification
     * @param notification the notification to be broadcast to the observer
     */
    void update(Notification notification);
}
