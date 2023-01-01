package basefiles;

import actions.Action;
import actions.CommandInvoker;
import basefiles.input.AppInput;
import basefiles.input.Movie;
import basefiles.input.User;

import java.util.ArrayList;
import java.util.List;

import static pages.Page.HOME_PAGE;
import static pages.Page.SEE_DETAILS_PAGE;
import static pages.Page.MOVIES_PAGE;

public final class Application {

    private static Application instance = null;

    private static String currentPage;
    private static User currentUser;
    private static List<Movie> currentMoviesList;
    private static AppInput appInput;
    private static List<Movie> filteredMovieList;

    private Application() {

    }

    /**
     * Maintain a single instance of an application throughout the program
     * @return  the application instance
     */
    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    /**
     * Start the POO-TV application
     * @return          the list of errors that will be mapped in the JSON output file
     */
    public List<ErrorOutput> start() {
        cleanUpTest();
        return execute();
    }

    private void cleanUpTest() {
        setCurrentPage(HOME_PAGE);
        setCurrentUser(null);
        setCurrentMoviesList(null);
        setFilteredMovieList(null);
    }

    private List<ErrorOutput> execute() {
        List<ErrorOutput> errorsOutput = new ArrayList<>();
        CommandInvoker invoker = new CommandInvoker();

        for (Action action : appInput.getActions()) {
            invoker.executeCommand(action);
            if (hasOutput(action)) {
                errorsOutput.add(action.getErrorOutput());
            }
        }
        return errorsOutput;
    }

    private static boolean hasOutput(final Action action) {
        ErrorOutput errorOutput = action.getErrorOutput();

        if (action.getType().equals("on page")
                && errorOutput.getError() == null
                && (action.getFeature().equals("filter")
                || action.getFeature().equals("search")
                || action.getFeature().equals("login")
                || action.getFeature().equals("register"))) {
            return true;
        }

        if (errorOutput.getError() == null
                && (currentPage.equals(MOVIES_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE))) {
            return true;
        }

        return errorOutput.getError() != null;
    }

    public static String getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(final String currentPage) {
        Application.currentPage = currentPage;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(final User currentUser) {
        Application.currentUser = currentUser;
    }

    public static List<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public static void setCurrentMoviesList(final List<Movie> currentMoviesList) {
        Application.currentMoviesList = currentMoviesList;
    }

    public static AppInput getAppInput() {
        return appInput;
    }

    public static void setAppInput(final AppInput appInput) {
        Application.appInput = appInput;
    }

    public static List<Movie> getFilteredMovieList() {
        return filteredMovieList;
    }

    public static void setFilteredMovieList(final List<Movie> filteredMovieList) {
        Application.filteredMovieList = filteredMovieList;
    }
}
