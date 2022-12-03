package pages;

public class LoggedHomePage extends Page {
    private MoviesPage movies;
    private UpgradesPage upgrades;
    private LogoutPage logout;

    public MoviesPage getMovies() {
        return movies;
    }

    public void setMovies(MoviesPage movies) {
        this.movies = movies;
    }

    public UpgradesPage getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(UpgradesPage upgrades) {
        this.upgrades = upgrades;
    }

    public LogoutPage getLogout() {
        return logout;
    }

    public void setLogout(LogoutPage logout) {
        this.logout = logout;
    }
}
