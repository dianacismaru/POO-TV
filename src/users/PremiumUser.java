package users;

public class PremiumUser extends User {
    private int numFreePremiumMovies;

    public PremiumUser() {
        this.numFreePremiumMovies = 15;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }
}
