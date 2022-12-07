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
            List<Movie> movieList = (Action.getFilteredMovieList() != null ?
                                     Action.getFilteredMovieList()
                                   : Action.getCurrentMoviesList());

            for (Movie movie: movieList) {
                if (movie.getName().equals(action.getMovie())) {
                    detailedMovie.add(movie);
                    break;
                }
            }
            if (detailedMovie.isEmpty()) {
                action.setErrorOutput(new ErrorOutput("movies"));
            } else {
                Action.setCurrentMoviesList(detailedMovie);
                action.setErrorOutput(new ErrorOutput());
            }
            return;
        }

        action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
    }

    public static void purchase(OnPageAction action) {
        if (!Action.getCurrentPage().equals("see details")) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        User user = new User(Action.getCurrentUser());

        if (user.getNumFreePremiumMovies() > 0
                && user.getCredentials().getAccountType().equals("premium")) {
            user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
        } else {
            user.setTokensCount(user.getTokensCount() - 2);
        }

        Movie currentMovie = Action.getCurrentMoviesList().get(0);
        List<Movie> purchasedMovies = new ArrayList<>(user.getPurchasedMovies());
        purchasedMovies.add(currentMovie);
        user.setPurchasedMovies(purchasedMovies);

        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentUser(user);
    }

    public static void watch(OnPageAction action) {
        if (!Action.getCurrentPage().equals("see details")) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        User user = new User(Action.getCurrentUser());
        Movie currentMovie = Action.getCurrentMoviesList().get(0);

        if (!user.getPurchasedMovies().contains(currentMovie)) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        List<Movie> watchedMovies = new ArrayList<>(user.getWatchedMovies());
        watchedMovies.add(currentMovie);
        user.setWatchedMovies(watchedMovies);
        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
    }

    public static void like(OnPageAction action) {
        if (!Action.getCurrentPage().equals("see details")) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        User user = new User(Action.getCurrentUser());
        Movie currentMovie = Action.getCurrentMoviesList().get(0);

        if (!user.getWatchedMovies().contains(currentMovie)) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        currentMovie = new Movie(Action.getCurrentMoviesList().get(0));
        List<Movie> likedMovies = new ArrayList<>(user.getLikedMovies());

        currentMovie.setNumLikes(currentMovie.getNumLikes() + 1);
        likedMovies.add(currentMovie);

        currentMovie.updateMovieInList(user.getPurchasedMovies());
        currentMovie.updateMovieInList(user.getWatchedMovies());
        currentMovie.updateMovieInList(user.getRatedMovies());
        currentMovie.updateMovieInList(Action.getAppInput().getMovies());

        Action.getCurrentMoviesList().set(0, currentMovie);
        user.setLikedMovies(likedMovies);
        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
    }

    public static void rate(OnPageAction action) {
        if (!Action.getCurrentPage().equals("see details")) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        User user = new User(Action.getCurrentUser());
        Movie currentMovie = Action.getCurrentMoviesList().get(0);

        if (!user.getWatchedMovies().contains(currentMovie) || action.getRate() > 5) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        currentMovie = new Movie(Action.getCurrentMoviesList().get(0));
        List<Movie> ratedMovies = new ArrayList<>(user.getRatedMovies());

        currentMovie.setNumRatings(currentMovie.getNumRatings() + 1);
        int sumRatings = currentMovie.getRating() * (currentMovie.getNumRatings() - 1)
                + action.getRate();
        currentMovie.setRating(sumRatings / currentMovie.getNumRatings());
        ratedMovies.add(currentMovie);

        currentMovie.updateMovieInList(user.getPurchasedMovies());
        currentMovie.updateMovieInList(user.getWatchedMovies());
        currentMovie.updateMovieInList(user.getLikedMovies());
        currentMovie.updateMovieInList(Action.getAppInput().getMovies());


        Action.getCurrentMoviesList().set(0, currentMovie);
        user.setRatedMovies(ratedMovies);
        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
    }
}
