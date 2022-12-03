package pages;

public class MoviesPage extends Page {
    private LoggedHomePage loggedHomePage;
    private DetailsPage details;
    private LogoutPage logout;

    public void search() {

    }

    public void filter() {

    }

    public LoggedHomePage getLoggedHomePage() {
        return loggedHomePage;
    }

    public void setLoggedHomePage(LoggedHomePage loggedHomePage) {
        this.loggedHomePage = loggedHomePage;
    }

    public DetailsPage getDetails() {
        return details;
    }

    public void setDetails(DetailsPage details) {
        this.details = details;
    }

    public LogoutPage getLogout() {
        return logout;
    }

    public void setLogout(LogoutPage logout) {
        this.logout = logout;
    }
}
