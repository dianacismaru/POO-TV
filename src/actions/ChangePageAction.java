package actions;

import basefiles.ErrorOutput;
import pages.Page;
import pages.LoginPage;
import pages.RegisterPage;
import pages.MoviesPage;
import pages.DetailsPage;
import pages.UpgradesPage;
import pages.LogoutPage;

import static pages.Page.LOGIN_PAGE;
import static pages.Page.LOGOUT_PAGE;
import static pages.Page.MOVIES_PAGE;
import static pages.Page.REGISTER_PAGE;
import static pages.Page.SEE_DETAILS_PAGE;
import static pages.Page.UPGRADES_PAGE;

public final class ChangePageAction extends Action {
    public ChangePageAction(final Action action) {
        this.setType(action.getType());
        this.setPage(action.getPage());
        this.setMovie(action.getMovie());
        this.setErrorOutput(new ErrorOutput());
    }

    @Override
    public void execute() {
        Page page = switch (this.getPage()) {
            case LOGIN_PAGE -> new LoginPage();
            case REGISTER_PAGE -> new RegisterPage();
            case MOVIES_PAGE -> new MoviesPage();
            case SEE_DETAILS_PAGE -> new DetailsPage();
            case UPGRADES_PAGE -> new UpgradesPage();
            case LOGOUT_PAGE -> new LogoutPage();
            default -> null;
        };

        assert page != null;
        page.changePage(this);
    }
}
