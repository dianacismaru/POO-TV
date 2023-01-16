package core;

import core.input.Movie;
import core.input.User;
import observer.Notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Utils {
    public static final int NUMBER_OF_GENRES = 10;

    private Utils() {

    }

    /**
     * Create a new notification with a movie recommendation if the current user is premium
     * @param errorsOutput the list of output nodes
     * @return the updated list of output nodes
     */
    public static List<ErrorOutput> makeRecommendation(final List<ErrorOutput> errorsOutput) {
        if (Application.getCurrentUser() == null
                || Application.getCurrentUser().getCredentials()
                .getAccountType().equals("standard")) {
            return errorsOutput;
        }

        User user = new User(Application.getCurrentUser());
        user.getNotifications().add(new Notification(getTopMovie(), "Recommendation"));

        Application.setCurrentUser(user);
        errorsOutput.add(new ErrorOutput());
        errorsOutput.get(errorsOutput.size() - 1).setCurrentMoviesList(null);
        return errorsOutput;
    }

    /**
     * Search for the best movie to recommend to the current premium user
     * @return the name of the recommended movie
     */
    public static String getTopMovie() {
        if (Application.getCurrentUser().getLikedMovies().size() == 0) {
            return "No recommendation";
        }

        List<String> topGenres = new ArrayList<>();
        List<Integer> userLikes = new ArrayList<>(Application.getCurrentUser()
                .getLikesForGenres());

        for (int i = 0; i < NUMBER_OF_GENRES; i++) {
            if (userLikes.get(i) != 0) {
                topGenres.add(Application.getGenreList().get(i).getName());
            }
        }

        userLikes.removeIf(integer -> integer == 0);

        int maxValue = userLikes.get(0);
        int maxValueIndex = -1;
        for (int i = 1; i < userLikes.size(); i++) {
            int value = userLikes.get(i);
            if (value > maxValue) {
                maxValue = value;
                maxValueIndex = i;
            }
        }

        String favGenre;

        if (maxValueIndex != -1) {
            favGenre = topGenres.get(maxValueIndex);
        } else {
            Collections.sort(topGenres);
            favGenre = topGenres.get(0);
        }

        List<Movie> movieList = new ArrayList<>(Application.getCurrentMoviesList());
        movieList.sort(new Comparator<>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                return o2.getNumLikes() - o1.getNumLikes();
            }
        });

        for (Movie movie: movieList) {
            if (movie.getGenres().contains(favGenre)) {
                List<Movie> watchedMovies = Application.getCurrentUser().getWatchedMovies();
                for (Movie watchedMovie: watchedMovies) {
                    if (!watchedMovie.getName().equals(movie.getName())) {
                        return movie.getName();
                    }
                }
            }
        }
        return "No recommendation";
    }
}
