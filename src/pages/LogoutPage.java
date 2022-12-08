package pages;

import actions.Action;
import actions.ChangePageAction;
import basefiles.ErrorOutput;

public class LogoutPage extends Page {
    public static void logout(ChangePageAction action) {
        String currentPage = Action.getCurrentPage();

        if (currentPage.equals(LOGGED_HOME_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE)
                || currentPage.equals(UPGRADES_PAGE)
                || currentPage.equals(MOVIES_PAGE)) {
            Action.setCurrentPage(HOME_PAGE);
            Action.setCurrentUser(null);
            Action.setCurrentMoviesList(null);
            return;
        }

        action.setErrorOutput(new ErrorOutput(HOME_PAGE));
    }
}
