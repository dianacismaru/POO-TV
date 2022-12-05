package actions;

import base.ErrorOutput;
import pages.DetailsPage;
import pages.MoviesPage;
import pages.UpgradesPage;

public final class ChangePageAction extends Action {
    public ChangePageAction(Action action) {
        this.setType(action.getType());
        this.setPage(action.getPage());
        this.setMovie(action.getMovie());
        this.setErrorOutput(new ErrorOutput());
    }

    @Override
    public void execute() {
        switch (this.getPage()) {
            case "login" -> login();
            case "register" -> register();
            case "movies" -> movies();
            case "see details" -> seeDetails();
            case "upgrades" -> upgrades();
            case "logout" -> logout();
        }
    }

    public void login() {
        if (getCurrentPage().equals("homepage")) {
            setCurrentPage("login");
        } else {
            setErrorOutput(new ErrorOutput(getCurrentPage()));
        }
    }

    public void register() {
        if (getCurrentPage().equals("homepage")) {
            setCurrentPage("register");
        } else {
            setErrorOutput(new ErrorOutput("homepage"));
        }
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
        if (getCurrentPage().equals("loggedHomepage")
                || getCurrentPage().equals("see details")
                || getCurrentPage().equals("upgrades")
                || getCurrentPage().equals("movies")) {
            setCurrentPage("homepage");
            setCurrentUser(null);
            setCurrentMoviesList(null);
        } else {
            setErrorOutput(new ErrorOutput("homepage"));
        }
    }
}
