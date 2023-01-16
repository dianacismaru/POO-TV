package core.input;

import core.Application;

import java.util.List;

public final class Contains {
    private List<String> actors;
    private List<String> genre;
    private List<String> country;

    /**
     * Filter the movies that contains the specified criteria
     * @param filteredMovies the list of movies to apply the filter on
     */
    public void contains(final List<Movie> filteredMovies) {
        for (Movie movie: Application.getCurrentMoviesList()) {
            boolean isMatching = true;

            if (actors != null) {
                for (String actor: actors) {
                    if (!movie.getActors().contains(actor)) {
                        isMatching = false;
                        break;
                    }
                }
            }

            if (isMatching && genre != null) {
                for (String genreElement: genre) {
                    if (!movie.getGenres().contains(genreElement)) {
                        isMatching = false;
                        break;
                    }
                }
            }
            if (isMatching) {
                filteredMovies.add(movie);
            }
        }
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(final List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenre() {
        return genre;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(final List<String> country) {
        this.country = country;
    }
}
