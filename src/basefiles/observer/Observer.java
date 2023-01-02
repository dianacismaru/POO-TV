package basefiles.observer;

// un user e considerat observer
public interface Observer {
    void update();

    //attach with subject to observe
    void addSubject(Subject sub);
}
