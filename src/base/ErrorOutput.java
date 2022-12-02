package base;

import movies.Movie;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class ErrorOutput {
    private String error;
    private List<Movie> currentMoviesList;
    private User currentUser;

    public ErrorOutput() {
        this.currentMoviesList = new ArrayList<>();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(List<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
