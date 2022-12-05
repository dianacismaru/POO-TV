package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import base.ErrorOutput;
import movies.Movie;
import users.User;

import java.util.ArrayList;
import java.util.List;

public final class DetailsPage extends Page {
    public static void seeDetails(ChangePageAction action) {
        if (Action.getCurrentPage().equals("movies")) {
            Action.setCurrentPage("see details");

            List<Movie> detailedMovie = new ArrayList<>();
            for (Movie movie: Action.getCurrentMoviesList()) {
                if (movie.getName().equals(action.getMovie())) {
                    detailedMovie.add(movie);
                    break;
                }
            }
            if (detailedMovie.isEmpty()) {
                action.setErrorOutput(new ErrorOutput("movies"));
            } else {
                action.setErrorOutput(new ErrorOutput());
                action.getErrorOutput().setCurrentMoviesList(detailedMovie);
            }
            return;
        }

        action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
    }

    // TODO: cumpara film, scade din lista, adauga in filme cumparate, scade tokeni
    public static void purchase(OnPageAction action) {
        if (!Action.getCurrentPage().equals("see details")) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        User user = new User(Action.getCurrentUser());

        if (user.getNumFreePremiumMovies() > 0) {
            user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
        } else {
            user.setTokensCount(user.getTokensCount() - 2);
        }

        List<Movie> purchasedMovies = new ArrayList<>();
        for (Movie movie: Action.getCurrentMoviesList()) {
            if (movie.getName().equals(action.getMovie())) {
                purchasedMovies.add(movie);
                break;
            }
        }
        user.setPurchasedMovies(purchasedMovies);
        // TODO daca filmul nu a fost gasit?

        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentMoviesList(purchasedMovies);
        action.getErrorOutput().setCurrentUser(user);
        //action.getErrorOutput().setCurrentMoviesList(user.getPurchasedMovies());
    }
}
