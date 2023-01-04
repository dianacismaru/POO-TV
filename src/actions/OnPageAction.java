package actions;

import basefiles.input.Credentials;
import basefiles.input.Filters;
import pages.DetailsPage;
import pages.LoginPage;
import pages.MoviesPage;
import pages.RegisterPage;
import pages.UpgradesPage;

public final class OnPageAction extends Action {
    private Credentials credentials;
    private int count;
    private String startsWith;
    private Filters filters;
    private double rate;
    private String subscribedGenre;

    @Override
    public void execute() {
        switch (this.getFeature()) {
            case "login" -> LoginPage.login(this);
            case "register" -> RegisterPage.register(this);
            case "search" -> MoviesPage.search(this);
            case "filter" -> MoviesPage.filter(this);
            case "purchase" -> DetailsPage.purchase(this);
            case "watch" -> DetailsPage.watch(this);
            case "like" -> DetailsPage.like(this);
            case "rate" -> DetailsPage.rate(this);
            case "subscribe" -> DetailsPage.subscribe(this);
            case "buy premium account" -> UpgradesPage.buyPremiumAccount(this);
            case "buy tokens" -> UpgradesPage.buyTokens(this);
            default -> System.out.println(INVALID_CASE);
        }
    }

    @Override
    public String getType() {
        return "on page";
    }

    /**
     * @return the credentials that this action uses
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * @param credentials the credentials to be set
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * @return the number of tokens that are bought
     */
    public int getCount() {
        return count;
    }

    /**
     * @return the string that will be checked for filtering
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * @return the filters that this action will apply
     */
    public Filters getFilters() {
        return filters;
    }

    /**
     * @return the rate that will be given to the current movie
     */
    public double getRate() {
        return rate;
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }
}
