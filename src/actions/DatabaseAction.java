package actions;

import core.Application;
import core.ErrorOutput;
import observer.Genre;
import core.input.Movie;
import core.input.User;
import observer.Notification;
import java.util.List;

public final class DatabaseAction extends Action {
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

        Notification notification = new Notification(addedMovie.getName(), "ADD");

        for (String genreString: addedMovie.getGenres()) {
            for (Genre genre: Application.getGenreList()) {
                if (genre.getName().equals(genreString)) {
                    genre.notifyObservers(notification);
                }
            }
        }

        setErrorOutput(new ErrorOutput());
    }

    private void delete() {
        Notification notification = new Notification(deletedMovie, "DELETE");

        for (Movie movie: Application.getAppInput().getMovies()) {
            if (movie.getName().equals(deletedMovie)) {
                for (User user: movie.getBuyers()) {
                    user.update(notification);
                }
                Application.getAppInput().getMovies().remove(movie);
                setErrorOutput(new ErrorOutput());
                return;
            }
        }
        setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
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
}
