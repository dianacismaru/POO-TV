package pages;

import actions.ChangePageAction;
import basefiles.Application;
import basefiles.ErrorOutput;

public final class LogoutPage extends Page {
    @Override
    public void changePage(final ChangePageAction action) {
        String currentPage = Application.getCurrentPage();

        if (currentPage.equals(LOGGED_HOME_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE)
                || currentPage.equals(UPGRADES_PAGE)
                || currentPage.equals(MOVIES_PAGE)) {
            Application.setCurrentPage(HOME_PAGE);
            Application.setCurrentUser(null);
            Application.setCurrentMoviesList(null);
            return;
        }

        action.setErrorOutput(new ErrorOutput(HOME_PAGE));
    }
}
