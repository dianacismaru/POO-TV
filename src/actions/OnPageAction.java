package actions;

import pages.LoginPage;
import pages.RegisterPage;

public final class OnPageAction extends Action {
    public OnPageAction(Action action) {
        this.setType(action.getType());
        this.setPage(action.getPage());
        this.setFeature(action.getFeature());
        this.setCredentials(action.getCredentials());
        this.setCount(action.getCount());
        this.setStartsWith(action.getStartsWith());
        this.setFilters(action.getFilters());
    }

    @Override
    public void execute() {
        switch (this.getFeature()) {
            case "login" -> login();
            case "register" -> register();
            case "search" -> search();
            case "filter" -> filter();
            case "purchase" -> purchase();
            case "watch" -> watch();
            case "like" -> like();
            case "rate" -> rate();
            case "buy premium account" -> buyPremiumAccount();
            case "buy tokens" -> buyTokens();
        }
    }

    public void login() {
        LoginPage.login(this);
    }

    public void register() {
        RegisterPage.register(this);
    }

    public void search() {

    }

    public void filter() {

    }

    public void purchase() {

    }

    public void watch() {

    }

    public void like() {

    }

    public void rate() {

    }

    public void buyPremiumAccount() {

    }

    public void buyTokens() {

    }
}
