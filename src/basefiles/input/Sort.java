package basefiles.input;

import java.util.Comparator;
import java.util.List;

public final class Sort {
    private String rating;
    private String duration;

    /**
     * Sort the movies
     * @param filteredMovies the list of movies to apply the filter on
     */
    public List<Movie> sort(final List<Movie> filteredMovies) {
        filteredMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(final Movie movie1, final Movie movie2) {
                int comparator;

                if (duration != null) {
                    if (duration.equals("increasing")) {
                        comparator = movie1.getDuration() - movie2.getDuration();
                    } else {
                        comparator = movie2.getDuration() - movie1.getDuration();
                    }
                    if (comparator != 0) {
                        return comparator;
                    }
                }

                if (rating != null) {
                    if (rating.equals("increasing")) {
                        comparator = (int) (movie1.getRating() - movie2.getRating());
                    } else {
                        comparator = (int) (movie2.getRating() - movie1.getRating());
                    }

                    return comparator;
                }

                return 0;
            }
        });
        return filteredMovies;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(final String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(final String duration) {
        this.duration = duration;
    }
}
