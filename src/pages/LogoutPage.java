package pages;

import actions.ChangePageAction;
import actions.CommandInvoker;
import basefiles.Application;
import basefiles.ErrorOutput;

public final class LogoutPage implements Page {
    @Override
    public void changePage(final ChangePageAction action) {
        String currentPage = Application.getCurrentPage();

        if (currentPage.equals(LOGGED_HOME_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE)
                || currentPage.equals(UPGRADES_PAGE)
                || currentPage.equals(MOVIES_PAGE)) {
            Application.getCurrentUser().updateInInput();
            Application.setCurrentPage(HOME_PAGE);
            Application.setCurrentUser(null);
            Application.setCurrentMoviesList(null);
            CommandInvoker.deleteHistory();
            action.setErrorOutput(new ErrorOutput());
            return;
        }

        action.setErrorOutput(new ErrorOutput(HOME_PAGE));
    }
}
