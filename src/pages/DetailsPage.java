package pages;

import actions.ChangePageAction;
import actions.OnPageAction;
import basefiles.Application;
import basefiles.ErrorOutput;
import basefiles.Genre;
import basefiles.input.Movie;
import basefiles.input.User;

import java.util.ArrayList;
import java.util.List;

import static basefiles.Utils.NUMBER_OF_GENRES;

public final class DetailsPage implements Page {
    private static final int MAXIMUM_RATING = 5;

    @Override
    public void changePage(final ChangePageAction action) {
        if (!Application.getCurrentPage().equals(MOVIES_PAGE)) {
            if (Application.getCurrentPage().equals(SEE_DETAILS_PAGE)) {
                action.setErrorOutput(new ErrorOutput());
                return;
            }
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        Application.setCurrentPage(SEE_DETAILS_PAGE);

        List<Movie> detailedMovie = new ArrayList<>();
        List<Movie> movieList = (Application.getFilteredMovieList() != null
                ? Application.getFilteredMovieList()
                : Application.getCurrentMoviesList());

        for (Movie movie: movieList) {
            if (movie.getName().equals(action.getMovie())) {
                detailedMovie.add(movie);
                break;
            }
        }

        if (detailedMovie.isEmpty()) {
            action.setErrorOutput(new ErrorOutput(MOVIES_PAGE));
        } else {
            Application.setCurrentMoviesList(detailedMovie);
            action.setErrorOutput(new ErrorOutput());
        }
    }

    /**
     * Purchase the current movie shown in the list, for the current user
     * @param action the current action
     */
    public static void purchase(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(SEE_DETAILS_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        User user = new User(Application.getCurrentUser());

        if (user.getNumFreePremiumMovies() > 0
                && user.getCredentials().getAccountType().equals("premium")) {
            user.setNumFreePremiumMovies(user.getNumFreePremiumMovies() - 1);
        } else {
            user.setTokensCount(user.getTokensCount() - 2);
        }

        Movie currentMovie = Application.getCurrentMoviesList().get(0);
        List<Movie> purchasedMovies = new ArrayList<>(user.getPurchasedMovies());

        if (user.getPurchasedMovies().contains(currentMovie)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }
        purchasedMovies.add(currentMovie);
        user.setPurchasedMovies(purchasedMovies);

        Application.setCurrentUser(user);

        for (Movie movie: Application.getAppInput().getMovies()) {
            if (movie.getName().equals(currentMovie.getName())) {
                movie.getBuyers().add(user);
            }
        }
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentUser(user);
    }

    /**
     * Watch a purchased movie
     * @param action the current action
     */
    public static void watch(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(SEE_DETAILS_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        User user = new User(Application.getCurrentUser());
        Movie currentMovie = Application.getCurrentMoviesList().get(0);

        if (!user.getPurchasedMovies().contains(currentMovie)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        List<Movie> watchedMovies = new ArrayList<>(user.getWatchedMovies());

        if (!user.getWatchedMovies().contains(currentMovie)) {
            watchedMovies.add(currentMovie);
            user.setWatchedMovies(watchedMovies);
            Application.setCurrentUser(user);
        }

        action.setErrorOutput(new ErrorOutput());
    }

    /**
     * Give a like for a watched movie
     * @param action the current action
     */
    public static void like(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(SEE_DETAILS_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        User user = new User(Application.getCurrentUser());
        Movie currentMovie = Application.getCurrentMoviesList().get(0);

        if (!user.getWatchedMovies().contains(currentMovie)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        currentMovie = new Movie(Application.getCurrentMoviesList().get(0));
        List<Movie> likedMovies = new ArrayList<>(user.getLikedMovies());

        currentMovie.setNumLikes(currentMovie.getNumLikes() + 1);
        likedMovies.add(currentMovie);

        currentMovie.updateMovieInList(user.getPurchasedMovies());
        currentMovie.updateMovieInList(user.getWatchedMovies());
        currentMovie.updateMovieInList(user.getRatedMovies());
        currentMovie.updateMovieInList(Application.getAppInput().getMovies());

        Application.getCurrentMoviesList().set(0, currentMovie);
        user.setLikedMovies(likedMovies);
        Application.setCurrentUser(user);
        for (int i = 0; i < NUMBER_OF_GENRES; i++) {
            for (String genre: currentMovie.getGenres()) {
                if (genre.equals(Application.getGenreList().get(i).getName())) {
                    user.getLikesForGenres().set(i, user.getLikesForGenres().get(i) + 1);
                }
            }
        }

        action.setErrorOutput(new ErrorOutput());
    }

    /**
     * Give a rate for a watched movie
     * @param action the current action
     */
    public static void rate(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(SEE_DETAILS_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        User user = new User(Application.getCurrentUser());
        Movie currentMovie = Application.getCurrentMoviesList().get(0);

        if (!user.getWatchedMovies().contains(currentMovie) || action.getRate() > MAXIMUM_RATING) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        currentMovie = new Movie(Application.getCurrentMoviesList().get(0));
        List<Movie> ratedMovies = new ArrayList<>(user.getRatedMovies());

        int numRatings = currentMovie.getNumRatings();
        boolean firstRate = false;
        if (currentMovie.getRatings().get(user.calculateIndex()) == 0) {
            numRatings++;
            firstRate = true;
        }
        currentMovie.setNumRatings(numRatings);
        currentMovie.getRatings().set(user.calculateIndex(), action.getRate());

        double sumRatings = currentMovie.getRatings().stream()
                .mapToDouble(Double::doubleValue).sum();
        currentMovie.setRating(sumRatings / currentMovie.getNumRatings());

        if (firstRate) {
            ratedMovies.add(currentMovie);
        }

        currentMovie.updateMovieInList(user.getPurchasedMovies());
        currentMovie.updateMovieInList(user.getWatchedMovies());
        currentMovie.updateMovieInList(user.getLikedMovies());
        currentMovie.updateMovieInList(Application.getAppInput().getMovies());

        Application.getCurrentMoviesList().set(0, currentMovie);
        user.setRatedMovies(ratedMovies);
        Application.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
    }

    /**
     * Subscribe the current user to the current movie's genres
     * @param action the current action
     */
    public static void subscribe(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(SEE_DETAILS_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        Movie currentMovie = Application.getCurrentMoviesList().get(0);

        if (!currentMovie.getGenres().contains(action.getSubscribedGenre())) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        for (Genre genre: Application.getCurrentUser().getSubscribedGenres()) {
            if (genre.getName().equals(action.getSubscribedGenre())) {
                action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
                return;
            }
        }

        for (Genre genre: Application.getGenreList()) {
            if (genre.getName().equals(action.getSubscribedGenre())) {
                genre.addObserver(Application.getCurrentUser());
                Application.getCurrentUser().getSubscribedGenres().add(genre);
            }
        }

        action.setErrorOutput(new ErrorOutput());
    }
}
