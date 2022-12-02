package users;

import movies.Movie;

import java.util.List;

public class User {
    Credentials credentials;
    private int tokensCount;
    private List<Movie> purchasedMovies;
    private List<Movie> watchedMovies;
    private List<Movie> likedMovies;
    private List<Movie> ratedMovies;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public List<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(List<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public List<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(List<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public List<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(List<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public List<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(List<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    @Override
    public String toString() {
        return "User{" +
                "credentials=" + credentials +
                ",\n tokensCount=" + tokensCount +
                ",\n purchasedMovies=" + purchasedMovies +
                ",\n watchedMovies=" + watchedMovies +
                ",\n likedMovies=" + likedMovies +
                ",\n ratedMovies=" + ratedMovies +
                "}\n";
    }
}
