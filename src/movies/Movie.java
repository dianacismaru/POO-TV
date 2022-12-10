package movies;

import java.util.ArrayList;
import java.util.List;

public final class Movie {
    private String name;
    private int year;
    private int duration;
    private List<String> genres;
    private List<String> actors;
    private List<String> countriesBanned;

    private int numLikes;
    private double rating = 0.00;
    private int numRatings;

    public Movie() {

    }

    public Movie(final Movie movie) {
        this.name = movie.name;
        this.year = movie.year;
        this.duration = movie.duration;
        this.genres = new ArrayList<>(movie.genres);
        this.actors = new ArrayList<>(movie.actors);
        this.countriesBanned = new ArrayList<>(movie.countriesBanned);
        this.rating = movie.rating;
        this.numLikes = movie.numLikes;
        this.numRatings = movie.numRatings;
    }

    /**
     * Reset the current movie in a specified list
     * @param movieList the list that is parsed
     */
    public void updateMovieInList(final List<Movie> movieList) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getName().equals(this.name)) {
                movieList.set(i, this);
                return;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(final List<String>  genres) {
        this.genres = genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(final List<String> actors) {
        this.actors = actors;
    }

    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final int numLikes) {
        this.numLikes = numLikes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final int numRatings) {
        this.numRatings = numRatings;
    }
}
