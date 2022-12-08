package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import basefiles.ErrorOutput;
import users.User;

public final class UpgradesPage extends Page {
    private static final int PRICE_FOR_PREMIUM_ACCOUNT = 10;

    public static void upgrades(ChangePageAction action) {
        String currentPage = Action.getCurrentPage();

        if (currentPage.equals(LOGGED_HOME_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE)) {
            Action.setCurrentPage(UPGRADES_PAGE);
        } else {
            action.setErrorOutput(new ErrorOutput(HOME_PAGE));
        }
    }

    public static void buyTokens(OnPageAction action) {
        User user = new User(Action.getCurrentUser());
        int currentBalance = Integer.parseInt(user.getCredentials().getBalance());
        int countTokens = action.getCount();

        user.getCredentials().setBalance(Integer.toString(currentBalance - countTokens));
        user.setTokensCount(user.getTokensCount() + action.getCount());

        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentUser(user);

    }

    public static void buyPremiumAccount(OnPageAction action) {
        User user = new User(Action.getCurrentUser());

        user.getCredentials().setAccountType("premium");
        user.setTokensCount(user.getTokensCount() - PRICE_FOR_PREMIUM_ACCOUNT);

        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentUser(user);
    }
}
