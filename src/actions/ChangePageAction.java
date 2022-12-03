package actions;

import base.ErrorOutput;

import java.util.ArrayList;

public final class ChangePageAction extends Action {
    public ChangePageAction(Action action) {
        this.setType(action.getType());
        this.setPage(action.getPage());
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
        if (getCurrentPage().equals("loggedHomepage")
                || getCurrentPage().equals("see details")
                || getCurrentPage().equals("upgrades")) {
            setCurrentPage("movies");
            setCurrentMoviesList(new ArrayList<>(getAppInput().getMovies()));
            setErrorOutput(new ErrorOutput());
        } else {
            setErrorOutput(new ErrorOutput("homepage"));
        }
    }

    public void seeDetails() {
        if (getCurrentPage().equals("movies")) {
            setCurrentPage("see details");
        } else {
            setErrorOutput(new ErrorOutput("homepage"));
        }
    }

    public void upgrades() {
        if (getCurrentPage().equals("loggedHomepage")
                || getCurrentPage().equals("see details")) {
            setCurrentPage("upgrades");
        } else {
            setErrorOutput(new ErrorOutput("homepage"));
        }
    }

    public void logout() {
        if (getCurrentPage().equals("loggedHomepage")
                || getCurrentPage().equals("see details")
                || getCurrentPage().equals("upgrades")) {
            setCurrentPage("homepage");
        } else {
            setErrorOutput(new ErrorOutput("homepage"));
        }
    }
}
