package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import base.ErrorOutput;
import movies.Movie;
import users.User;

import java.util.ArrayList;
import java.util.List;

public final class MoviesPage extends Page {
    public static void movies(ChangePageAction action) {
        String currentPage = Action.getCurrentPage();

        if (currentPage.equals("loggedHomepage")
                || currentPage.equals("see details")
                || currentPage.equals("upgrades")) {
            Action.setCurrentPage("movies");

            List<Movie> movies = new ArrayList<>();
            String currentUserCountry = Action.getCurrentUser().getCredentials().getCountry();
            for (Movie movie: Action.getAppInput().getMovies()) {
                if (!movie.getCountriesBanned().contains(currentUserCountry)) {
                    movies.add(movie);
                }
            }
            Action.setCurrentMoviesList(new ArrayList<>(movies));
            action.setErrorOutput(new ErrorOutput());
            return;
        }
        action.setErrorOutput(new ErrorOutput("homepage"));
    }

    public static void search(OnPageAction action) {
        if (!Action.getCurrentPage().equals("movies")) {
            action.setErrorOutput(new ErrorOutput("homepage"));
            return;
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie: Action.getCurrentMoviesList()) {
            if (movie.getName().startsWith(action.getStartsWith())) {
                filteredMovies.add(movie);
            }
        }

        /*
                Action.setCurrentMoviesList(new ArrayList<>(filteredMovies));
                NU cred ca e nevoie sa modific lista generala
         */

        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentMoviesList(filteredMovies);
    }
    public void filter() {

    }
}
