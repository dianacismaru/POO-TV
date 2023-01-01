package basefiles.input;

import basefiles.Application;

import java.util.ArrayList;
import java.util.List;

public final class Filters {
    private Sort sort;
    private Contains contains;

    /**
     * Filter the movies by the specified criteria
     */
    public void filter() {
        List<Movie> filteredMovies = new ArrayList<>();

        if (contains != null) {
            contains.contains(filteredMovies);
        }

        if (sort != null) {
            if (filteredMovies.isEmpty()) {
                filteredMovies = new ArrayList<>(Application.getCurrentMoviesList());
            }
            filteredMovies = sort.sort(filteredMovies);
        }

        Application.setFilteredMovieList(filteredMovies);
    }

    public Sort getSort() {
        return sort;
    }

    public Contains getContains() {
        return contains;
    }
}
