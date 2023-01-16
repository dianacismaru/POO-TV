package observer;

import java.util.ArrayList;
import java.util.List;

public final class Genre implements Subject {
    private String name;
    private final List<Observer> observers;

    public Genre(final String name) {
        this.name = name;
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(final Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers(final Notification notification) {
        for (Observer observer: observers) {
            observer.update(notification);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
