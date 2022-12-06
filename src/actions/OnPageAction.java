package actions;

import pages.*;

public final class OnPageAction extends Action {
    public OnPageAction(Action action) {
        this.setType(action.getType());
        this.setPage(action.getPage());
        this.setFeature(action.getFeature());
        this.setCredentials(action.getCredentials());
        this.setCount(action.getCount());
        this.setStartsWith(action.getStartsWith());
        this.setFilters(action.getFilters());
        this.setRate(action.getRate());
        this.setMovie(action.getMovie());
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
        MoviesPage.search(this);
    }

    public void filter() {
        MoviesPage.filter(this);
    }

    public void purchase() {
        DetailsPage.purchase(this);
    }

    public void watch() {
        DetailsPage.watch(this);
    }

    public void like() {
        DetailsPage.like(this);
    }

    public void rate() {
        DetailsPage.rate(this);
    }

    public void buyPremiumAccount() {
        UpgradesPage.buyPremiumAccount(this);
    }

    public void buyTokens() {
        UpgradesPage.buyTokens(this);
    }
}
