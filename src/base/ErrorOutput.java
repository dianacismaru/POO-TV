package base;

import actions.Action;
import movies.Movie;
import users.User;

import java.util.ArrayList;
import java.util.List;

public final class ErrorOutput {
    private String error;
    private List<Movie> currentMoviesList;
    private User currentUser;

    public ErrorOutput() {
        this.error = null;
        if (Action.getCurrentMoviesList() == null) {
            this.currentMoviesList = new ArrayList<>();
        } else {
            this.currentMoviesList = new ArrayList<>(Action.getCurrentMoviesList());
        }
        this.currentUser = Action.getCurrentUser();
    }

    public ErrorOutput(String currentPage) {
        this();
        this.error = "Error";
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = null;
        Action.setCurrentPage(currentPage);
        Action.setCurrentUser(null);
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
