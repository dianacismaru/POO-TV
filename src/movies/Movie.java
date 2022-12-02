package movies;

import java.util.Arrays;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private String[] genres;
    private String[] actors;
    private String[] countriesBanned;

    private double rating;
    private int numLikes;
    private int numRatings;

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

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String[] getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(String[] countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ",\n year=" + year +
                ",\n duration=" + duration +
                ",\n genres=" + Arrays.toString(genres) +
                ",\n actors=" + Arrays.toString(actors) +
                ",\n countriesBanned=" + Arrays.toString(countriesBanned) +
                ",\n rating=" + rating +
                ",\n numLikes=" + numLikes +
                ",\n numRatings=" + numRatings +
                "}\n";
    }
}
