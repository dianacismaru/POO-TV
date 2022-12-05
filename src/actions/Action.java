package actions;

import base.AppInput;
import base.ErrorOutput;
import base.filters.Filters;
import movies.Movie;
import users.Credentials;
import users.User;
import visitor.ClientVisitor;
import visitor.Visitable;

import java.util.List;

public class Action implements Visitable {
    private String type;
    private String page;
    private String feature;
    private Credentials credentials;
    private int count;
    private String startsWith;
    private Filters filters;
    private String movie;
    private String objectType;
    private String rate;

    private ErrorOutput errorOutput;
    private static String currentPage;
    private static User currentUser;
    private static List<Movie> currentMoviesList;
    private static AppInput appInput;

    public void execute() {
        Action action;
        if (type.equals("change page")) {
            action = new ChangePageAction(this);
        } else {
            action = new OnPageAction(this);
        }
        action.execute();
        this.setErrorOutput(action.getErrorOutput());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public ErrorOutput getErrorOutput() {
        return errorOutput;
    }

    public void setErrorOutput(ErrorOutput errorOutput) {
        this.errorOutput = errorOutput;
    }

    public static String getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(String currentPage) {
        Action.currentPage = currentPage;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Action.currentUser = currentUser;
    }

    public static List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public static void setCurrentMoviesList(List<Movie> currentMoviesList) {
        Action.currentMoviesList = currentMoviesList;
    }

    public static AppInput getAppInput() {
        return appInput;
    }

    public static void setAppInput(AppInput appInput) {
        Action.appInput = appInput;
    }

    @Override
    public void accept(ClientVisitor visitor) {
        visitor.visit(this);
    }
}
