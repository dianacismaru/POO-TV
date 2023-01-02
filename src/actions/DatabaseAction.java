package actions;

import basefiles.Application;
import basefiles.ErrorOutput;
import basefiles.input.Movie;
import basefiles.observer.Notification;
import basefiles.observer.NotificationService;
import basefiles.observer.Observer;
import basefiles.observer.Subject;

import java.util.List;

public final class DatabaseAction extends Action implements Subject {
    private Movie addedMovie;
    private String deletedMovie;

    @Override
    public void execute() {
        if (this.getFeature().equals("add")) {
            add();
        } else {
            delete();
        }
    }

    @Override
    public String getType() {
        return "database";
    }

    private void add() {
        List<Movie> movieList = Application.getAppInput().getMovies();

        for (Movie movie: movieList) {
            if (movie.getName().equals(addedMovie.getName())) {
                setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
                return;
            }
        }

        movieList.add(addedMovie);
        Application.getAppInput().setMovies(movieList);

        // TODO: notifica utilizatorii abonati la genurile filmului nou
        Notification notification = new Notification(addedMovie.getName(), "ADD");
        NotificationService.doNotify(notification);
        // notiy_observers

        setErrorOutput(new ErrorOutput());
    }

    private void delete() {
        boolean foundMovie = Application.getAppInput().getMovies()
                .removeIf(movie -> movie.getName().equals(deletedMovie));

        if (!foundMovie) {
            setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        // TODO:
        // Notification notification = new Notification(deletedMovie, "DELETE");
        // notif: toti utilizatorii care au cumparat filmul sters
        // premium -> numFreeMovies++
        // standard -> tokensCount += 2
        // sterge filmul din toate listele utilizatorilor

        setErrorOutput(new ErrorOutput());
    }

    public Movie getAddedMovie() {
        return addedMovie;
    }

    public void setAddedMovie(final Movie addedMovie) {
        this.addedMovie = addedMovie;
    }

    public String getDeletedMovie() {
        return deletedMovie;
    }

    public void setDeletedMovie(final String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    @Override
    public void register(Observer obj) {

    }

    @Override
    public void unregister(Observer obj) {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public Object getUpdate(Observer obj) {
        return null;
    }
}
