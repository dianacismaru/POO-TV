package pages;

import static pages.Page.LOGIN_PAGE;
import static pages.Page.REGISTER_PAGE;
import static pages.Page.MOVIES_PAGE;
import static pages.Page.SEE_DETAILS_PAGE;
import static pages.Page.UPGRADES_PAGE;
import static pages.Page.LOGOUT_PAGE;

public final class PageFactory {
    private PageFactory() {

    }

    /**
     * Create a specific type of page using Factory Method Pattern
     * @param pageType the type of page to create
     * @return a new Page object of the specified type, or null if the pageType is not recognized
     */
    public static Page createPage(final String pageType) {
        return switch (pageType) {
            case LOGIN_PAGE -> new LoginPage();
            case REGISTER_PAGE -> new RegisterPage();
            case MOVIES_PAGE -> new MoviesPage();
            case SEE_DETAILS_PAGE -> new DetailsPage();
            case UPGRADES_PAGE -> new UpgradesPage();
            case LOGOUT_PAGE -> new LogoutPage();
            default -> null;
        };
    }
}
