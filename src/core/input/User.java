package core.input;

import core.Application;
import observer.Genre;
import observer.Notification;
import observer.Observer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static core.Utils.NUMBER_OF_GENRES;

public final class User implements Observer {
    private static final int NUM_FREE_PREMIUM_MOVIES = 15;
    private Credentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies = NUM_FREE_PREMIUM_MOVIES;
    private List<Movie> purchasedMovies = new ArrayList<>();
    private List<Movie> watchedMovies = new ArrayList<>();
    private List<Movie> likedMovies = new ArrayList<>();
    private List<Movie> ratedMovies = new ArrayList<>();
    private List<Notification> notifications = new ArrayList<>();

    @JsonIgnore
    private List<Genre> subscribedGenres = new ArrayList<>();

    @JsonIgnore
        private List<Integer> likesForGenres = new ArrayList<>(Stream.generate(() -> 0)
            .limit(NUMBER_OF_GENRES).collect(Collectors.toList()));

    public User() {

    }

    public User(final User user) {
        this.credentials = new Credentials(user.credentials);
        this.tokensCount = user.tokensCount;
        this.numFreePremiumMovies = user.numFreePremiumMovies;
        this.purchasedMovies = new ArrayList<>(user.purchasedMovies);
        this.watchedMovies = new ArrayList<>(user.watchedMovies);
        this.likedMovies = new ArrayList<>(user.likedMovies);
        this.ratedMovies = new ArrayList<>(user.ratedMovies);
        this.notifications = new ArrayList<>(user.notifications);
        this.subscribedGenres = new ArrayList<>(user.subscribedGenres);
        this.likesForGenres = new ArrayList<>(user.likesForGenres);
    }

    public User(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Calculate the index of the current user in the list of users
     * @return the index of the current user in the list of users, or -1 if the user is not found
     */
    public int calculateIndex() {
        List<User> users = Application.getAppInput().getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).credentials.getName().equals(this.credentials.getName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Update the current user in the list of users
     */
    public void updateInInput() {
        List<User> users = Application.getAppInput().getUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).credentials.getName().equals(this.credentials.getName())) {
                users.set(i, new User(this));
            }
        }
    }

    /**
     * Update the current user's lists of movies
     */
    public void updateLists() {
        for (Movie movie: Application.getAppInput().getMovies()) {
            movie.updateMovieInList(this.purchasedMovies);
            movie.updateMovieInList(this.watchedMovies);
            movie.updateMovieInList(this.likedMovies);
            movie.updateMovieInList(this.ratedMovies);
        }
    }

    @Override
    public void update(final Notification notification) {
        if (notification.getMessage().equals("DELETE")) {
            if (credentials.getAccountType().equals("premium")) {
                numFreePremiumMovies++;
            } else {
                tokensCount += 2;
            }

            purchasedMovies.removeIf(movie -> movie.getName().equals(notification.getMovieName()));
            watchedMovies.removeIf(movie -> movie.getName().equals(notification.getMovieName()));
            likedMovies.removeIf(movie -> movie.getName().equals(notification.getMovieName()));
            ratedMovies.removeIf(movie -> movie.getName().equals(notification.getMovieName()));
        }

        notifications.add(notification);
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public List<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final List<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final List<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public List<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final List<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final List<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Genre> getSubscribedGenres() {
        return subscribedGenres;
    }

    public List<Integer> getLikesForGenres() {
        return likesForGenres;
    }
}
