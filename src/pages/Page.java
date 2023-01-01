package pages;

import actions.ChangePageAction;

public interface Page {
    String HOME_PAGE = "homepage";
    String LOGGED_HOME_PAGE = "loggedHomepage";
    String LOGIN_PAGE = "login";
    String REGISTER_PAGE = "register";
    String MOVIES_PAGE = "movies";
    String SEE_DETAILS_PAGE = "see details";
    String UPGRADES_PAGE = "upgrades";
    String LOGOUT_PAGE = "logout";

    /**
     * Change the current page in the application
     * @param action    the current action
     */
    void changePage(ChangePageAction action);
}
