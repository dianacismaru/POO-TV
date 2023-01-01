package actions;

import basefiles.input.Movie;

public final class DatabaseAction extends Action {
    private Movie addedMovie;
    private String deletedMovie;

    @Override
    public void execute() {

    }

    @Override
    public String getType() {
        return "database";
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
}
