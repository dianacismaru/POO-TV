package actions;

import basefiles.AppInput;
import basefiles.ErrorOutput;
import basefiles.filters.Filters;
import movies.Movie;
import basefiles.Credentials;
import users.User;

import java.util.List;

public class Action {
    private String type;
    private String page;
    private String feature;
    private Credentials credentials;
    private int count;
    private String startsWith;
    private Filters filters;
    private String movie;
    private int rate;

    private ErrorOutput errorOutput;
    private static String currentPage;
    private static User currentUser;
    private static List<Movie> currentMoviesList;
    private static AppInput appInput;
    private static List<Movie> filteredMovieList;

    protected static final String INVALID_CASE = "Invalid case!";

    /**
     * Execute an input action, depending on its subclass
     */
    public void execute() {
        Action action = (type.equals("change page")
                         ? new ChangePageAction(this)
                         : new OnPageAction(this));
        action.execute();
        setErrorOutput(action.getErrorOutput());
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(final String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(final String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(final Filters filters) {
        this.filters = filters;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(final String movie) {
        this.movie = movie;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(final int rate) {
        this.rate = rate;
    }

    public ErrorOutput getErrorOutput() {
        return errorOutput;
    }

    public void setErrorOutput(final ErrorOutput errorOutput) {
        this.errorOutput = errorOutput;
    }

    public static String getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(final String currentPage) {
        Action.currentPage = currentPage;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(final User currentUser) {
        Action.currentUser = currentUser;
    }

    public static List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public static void setCurrentMoviesList(final List<Movie> currentMoviesList) {
        Action.currentMoviesList = currentMoviesList;
    }

    public static AppInput getAppInput() {
        return appInput;
    }

    public static void setAppInput(final AppInput appInput) {
        Action.appInput = appInput;
    }

    public static List<Movie> getFilteredMovieList() {
        return filteredMovieList;
    }

    public static void setFilteredMovieList(final List<Movie> filteredMovieList) {
        Action.filteredMovieList = filteredMovieList;
    }
}
