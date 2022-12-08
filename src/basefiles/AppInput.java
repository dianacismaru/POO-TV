package basefiles;

import actions.Action;
import movies.Movie;
import users.User;

import java.util.List;

public final class AppInput {
    private List<User> users;
    private List<Movie> movies;
    private List<Action> actions;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
