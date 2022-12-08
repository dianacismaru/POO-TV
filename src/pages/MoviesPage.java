package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import basefiles.ErrorOutput;
import basefiles.filters.Contains;
import basefiles.filters.Sort;
import movies.Movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class MoviesPage extends Page {
    public static void movies(ChangePageAction action) {
        String currentPage = Action.getCurrentPage();

        if (currentPage.equals(MOVIES_PAGE)) {
            action.setErrorOutput(new ErrorOutput());
            return;
        }

        if (currentPage.equals(LOGGED_HOME_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE)
                || currentPage.equals(UPGRADES_PAGE)) {
            Action.setCurrentPage(MOVIES_PAGE);

            List<Movie> movies = new ArrayList<>();
            String currentUserCountry = Action.getCurrentUser().getCredentials().getCountry();
            for (Movie movie: Action.getAppInput().getMovies()) {
                if (!movie.getCountriesBanned().contains(currentUserCountry)) {
                    movies.add(movie);
                }
            }
            Action.setCurrentMoviesList(new ArrayList<>(movies));
            action.setErrorOutput(new ErrorOutput());
            return;
        }

        action.setErrorOutput(new ErrorOutput(currentPage));
    }

    public static void search(OnPageAction action) {
        if (!Action.getCurrentPage().equals(MOVIES_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie: Action.getCurrentMoviesList()) {
            if (movie.getName().startsWith(action.getStartsWith())) {
                filteredMovies.add(movie);
            }
        }

        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentMoviesList(filteredMovies);
    }

    public static void filter(OnPageAction action) {
        if (!Action.getCurrentPage().equals(MOVIES_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        List<Movie> filteredMovies = new ArrayList<>();

        if (action.getFilters().getContains() != null) {
            Contains contains = action.getFilters().getContains();
            for (Movie movie: Action.getCurrentMoviesList()) {
                boolean isMatching = true;

                if (contains.getActors() != null) {
                    for (String actor: contains.getActors()) {
                        if (!movie.getActors().contains(actor)) {
                            isMatching = false;
                            break;
                        }
                    }
                }

                if (isMatching && contains.getGenre() != null) {
                    for (String genre: contains.getGenre()) {
                        if (!movie.getGenres().contains(genre)) {
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

        if (action.getFilters().getSort() != null) {
            Sort sortFilter = action.getFilters().getSort();

            if (filteredMovies.isEmpty()) {
                filteredMovies = new ArrayList<>(Action.getCurrentMoviesList());
            }
            filteredMovies.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie movie1, Movie movie2) {
                    int comparator;

                    if (sortFilter.getDuration() != null) {
                        if (sortFilter.getDuration().equals("increasing")) {
                            comparator = movie1.getDuration() - movie2.getDuration();
                        } else {
                            comparator = movie2.getDuration() - movie1.getDuration();
                        }
                        if (comparator != 0) {
                            return comparator;
                        }
                    }

                    if (sortFilter.getRating() != null) {
                        if (sortFilter.getRating().equals("increasing")) {
                            comparator = movie1.getRating() - movie2.getRating();
                        } else {
                            comparator = movie2.getRating() - movie1.getRating();
                        }

                        return comparator;
                    }

                    return 0;
                }
            });
        }

        Action.setFilteredMovieList(filteredMovies);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentMoviesList(filteredMovies);
    }
}
