package basefiles;

import actions.Action;

import java.util.ArrayList;
import java.util.List;

import static pages.Page.HOME_PAGE;

public final class Application {

    private static Application instance = null;

    private Application() {

    }

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public List<ErrorOutput> start(final AppInput appInput) {
        initialize(appInput);
        return execute(appInput);
    }

    private void initialize(final AppInput appInput) {
        Action.setCurrentPage(HOME_PAGE);
        Action.setAppInput(appInput);
        Action.setCurrentUser(null);
        Action.setCurrentMoviesList(null);
        Action.setFilteredMovieList(null);
    }

    private List<ErrorOutput> execute(final AppInput appInput) {
        List<ErrorOutput> errorsOutput = new ArrayList<>();

        for (Action action : appInput.getActions()) {
            action.execute();

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

       /* if (errorOutput.getError() == null
                && (action.getPage().equals("movies")
                || action.getPage().equals("see details"))) {
            return true;
        }*/
        if (errorOutput.getError() == null
                && (Action.getCurrentPage().equals("movies")
                || Action.getCurrentPage().equals("see details"))) {
            return true;
        }

        return errorOutput.getError() != null;
    }
}
