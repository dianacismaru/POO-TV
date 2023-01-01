package actions;

import pages.DetailsPage;
import pages.LoginPage;
import pages.MoviesPage;
import pages.RegisterPage;
import pages.UpgradesPage;

public final class OnPageAction extends Action {
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
            default -> System.out.println(INVALID_CASE);
        }
    }

    /**
     * Perform 'login' action in the Login page
     */
    public void login() {
        LoginPage.login(this);
    }

    /**
     * Perform 'register' action in the Register page
     */
    public void register() {
        RegisterPage.register(this);
    }

    /**
     * Perform 'search' action in the Movies page
     */
    public void search() {
        MoviesPage.search(this);
    }

    /**
     * Perform 'filter' action in the Movies page
     */
    public void filter() {
        MoviesPage.filter(this);
    }

    /**
     * Perform 'purchase' action in the See Details page
     */
    public void purchase() {
        DetailsPage.purchase(this);
    }

    /**
     * Perform 'watch' action in the See Details page
     */
    public void watch() {
        DetailsPage.watch(this);
    }

    /**
     * Perform 'like' action in the See Details page
     */
    public void like() {
        DetailsPage.like(this);
    }

    /**
     * Perform 'rate' action in the See Details page
     */
    public void rate() {
        DetailsPage.rate(this);
    }

    /**
     * Perform 'buy premium account' action in the Upgrades page
     */
    public void buyPremiumAccount() {
        UpgradesPage.buyPremiumAccount(this);
    }

    /**
     * Perform 'buy tokens' action in the Upgrades page
     */
    public void buyTokens() {
        UpgradesPage.buyTokens(this);
    }

    @Override
    public String getType() {
        return "on page";
    }
}
