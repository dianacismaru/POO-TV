package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import basefiles.ErrorOutput;
import movies.Movie;
import users.User;

import java.util.ArrayList;
import java.util.List;

public final class DetailsPage extends Page {
    private static final int MAXIMUM_RATING = 5;

    @Override
    public void changePage(final ChangePageAction action) {
        if (!Action.getCurrentPage().equals(MOVIES_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
        }

        Action.setCurrentPage(SEE_DETAILS_PAGE);

        List<Movie> detailedMovie = new ArrayList<>();
        List<Movie> movieList = (Action.getFilteredMovieList() != null
                ? Action.getFilteredMovieList()
                : Action.getCurrentMoviesList());

        for (Movie movie: movieList) {
            if (movie.getName().equals(action.getMovie())) {
                detailedMovie.add(movie);
                break;
            }
        }

        if (detailedMovie.isEmpty()) {
            action.setErrorOutput(new ErrorOutput(MOVIES_PAGE));
        } else {
            Action.setCurrentMoviesList(detailedMovie);
            action.setErrorOutput(new ErrorOutput());
        }
    }

    /**
     * Purchase the current movie shown in the list, for the current user
     * @param action the current action
     */
    public static void purchase(final OnPageAction action) {
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

    /**
     * Watch a purchased movie
     * @param action the current action
     */
    public static void watch(final OnPageAction action) {
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

    /**
     * Give a like for a watched movie
     * @param action the current action
     */
    public static void like(final OnPageAction action) {
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

    /**
     * Give a rate for a watched movie
     * @param action the current action
     */
    public static void rate(final OnPageAction action) {
        if (!Action.getCurrentPage().equals("see details")) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        User user = new User(Action.getCurrentUser());
        Movie currentMovie = Action.getCurrentMoviesList().get(0);

        if (!user.getWatchedMovies().contains(currentMovie) || action.getRate() > MAXIMUM_RATING) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        currentMovie = new Movie(Action.getCurrentMoviesList().get(0));
        List<Movie> ratedMovies = new ArrayList<>(user.getRatedMovies());

        currentMovie.setNumRatings(currentMovie.getNumRatings() + 1);
        double sumRatings = currentMovie.getRating() * (currentMovie.getNumRatings() - 1)
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
