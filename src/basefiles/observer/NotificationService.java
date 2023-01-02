package basefiles.observer;

import basefiles.input.User;

import java.util.ArrayList;
import java.util.List;

public final class NotificationService {
    private final List<User> subscribers;

    public NotificationService() {
        subscribers = new ArrayList<>();
    }

    public void subscribe(final User user) {
        subscribers.add(user);
    }

    public static void doNotify(final Notification notification) {
        //subscribers.forEach(user -> user.getNotifications().add(notification));
    }
}
