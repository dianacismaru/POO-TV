package actions;

import basefiles.ErrorOutput;
import pages.DetailsPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.MoviesPage;
import pages.RegisterPage;
import pages.UpgradesPage;

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
        switch (this.getPage()) {
            case LOGIN_PAGE -> login();
            case REGISTER_PAGE -> register();
            case MOVIES_PAGE -> movies();
            case SEE_DETAILS_PAGE -> seeDetails();
            case UPGRADES_PAGE -> upgrades();
            case LOGOUT_PAGE -> logout();
            default -> System.out.println(INVALID_CASE);
        }
    }

    public void login() {
        LoginPage.login(this);
    }

    public void register() {
        RegisterPage.register(this);
    }

    public void movies() {
        MoviesPage.movies(this);
    }

    public void seeDetails() {
        DetailsPage.seeDetails(this);
    }

    public void upgrades() {
        UpgradesPage.upgrades(this);
    }

    public void logout() {
        LogoutPage.logout(this);
    }
}
