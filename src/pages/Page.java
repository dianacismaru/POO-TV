package pages;

import actions.ChangePageAction;

public abstract class Page {
    public static final String HOME_PAGE = "homepage";
    public static final String LOGGED_HOME_PAGE = "loggedHomepage";
    public static final String LOGIN_PAGE = "login";
    public static final String REGISTER_PAGE = "register";
    public static final String MOVIES_PAGE = "movies";
    public static final String SEE_DETAILS_PAGE = "see details";
    public static final String UPGRADES_PAGE = "upgrades";
    public static final String LOGOUT_PAGE = "logout";

    public abstract void changePage(ChangePageAction action);
}
