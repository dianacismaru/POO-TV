package movies;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private int year;
    private int duration;
    List<String> genres;
    List<String> actors;
    List<String> countriesBanned;

    private int numLikes;
    private int rating;
    private int numRatings;

    public Movie() {

    }

    public Movie(Movie movie) {
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

    public void updateMovieInList(List<Movie> movieList) {
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

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String>  genres) {
        this.genres = genres;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public int getRating() {
        return rating;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }
}
