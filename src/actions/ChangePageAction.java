package actions;

import base.ErrorOutput;

public class ChangePageAction extends Action {
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
            case "upgrades" -> upgrades();
            case "logout" -> logout();
            case "see details" -> seeDetails();
        }
    }

    public void login() {
        if (getCurrentPage().equals("homepage")) {
            setCurrentPage("login");
        } else {
            setErrorOutput(new ErrorOutput("Error"));
        }
    }

    public void register() {
        if (getCurrentPage().equals("homepage")) {
            setCurrentPage("register");
        } else {
            setErrorOutput(new ErrorOutput("Error"));
        }
    }

    public void movies() {

    }

    public void upgrades() {

    }

    public void logout() {

    }

    public void seeDetails() {

    }
}
