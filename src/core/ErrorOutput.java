package core;

import core.input.Movie;
import core.input.User;

import java.util.ArrayList;
import java.util.List;

public final class ErrorOutput {
    private String error;
    private List<Movie> currentMoviesList;
    private User currentUser;

    public ErrorOutput() {
        this.error = null;
        if (Application.getCurrentMoviesList() == null) {
            this.currentMoviesList = new ArrayList<>();
        } else {
            this.currentMoviesList = new ArrayList<>(Application.getCurrentMoviesList());
        }
        this.currentUser = Application.getCurrentUser();
    }

    public ErrorOutput(final String currentPage) {
        this();
        this.error = "Error";
        this.currentMoviesList = new ArrayList<>();
        this.currentUser = null;
        Application.setCurrentPage(currentPage);
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final List<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }
}
