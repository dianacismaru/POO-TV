package pages;

import actions.ChangePageAction;
import actions.OnPageAction;
import basefiles.Application;
import basefiles.ErrorOutput;
import basefiles.input.Movie;

import java.util.ArrayList;
import java.util.List;

public final class MoviesPage implements Page {
    @Override
    public void changePage(final ChangePageAction action) {
        String currentPage = Application.getCurrentPage();

        if (currentPage.equals(MOVIES_PAGE)) {
            action.setErrorOutput(new ErrorOutput());
            return;
        }

        if (currentPage.equals(LOGGED_HOME_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE)
                || currentPage.equals(UPGRADES_PAGE)) {
            Application.setCurrentPage(MOVIES_PAGE);

            List<Movie> movies = new ArrayList<>();
            String currentUserCountry = Application.getCurrentUser().getCredentials().getCountry();
            for (Movie movie: Application.getAppInput().getMovies()) {
                if (!movie.getCountriesBanned().contains(currentUserCountry)) {
                    movies.add(movie);
                }
            }
            Application.setCurrentMoviesList(new ArrayList<>(movies));
            action.setErrorOutput(new ErrorOutput());
            return;
        }

        action.setErrorOutput(new ErrorOutput(currentPage));
    }

    /**
     * Search for the movies that start with the specified substring
     * @param action the current action
     */
    public static void search(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(MOVIES_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie: Application.getCurrentMoviesList()) {
            if (movie.getName().startsWith(action.getStartsWith())) {
                filteredMovies.add(movie);
            }
        }

        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentMoviesList(filteredMovies);
    }

    /**
     * Filter the movies by the specified criteria
     * @param action the current action
     */
    public static void filter(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(MOVIES_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        action.getFilters().filter();

        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentMoviesList(Application.getFilteredMovieList());
    }
}
