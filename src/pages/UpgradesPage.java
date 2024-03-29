package pages;

import actions.ChangePageAction;
import actions.OnPageAction;
import core.Application;
import core.ErrorOutput;
import core.input.User;

public final class UpgradesPage implements Page {
    private static final int PRICE_FOR_PREMIUM_ACCOUNT = 10;

    @Override
    public void changePage(final ChangePageAction action) {
        String currentPage = Application.getCurrentPage();

        if (currentPage.equals(LOGGED_HOME_PAGE)
                || currentPage.equals(SEE_DETAILS_PAGE)
                || currentPage.equals(UPGRADES_PAGE)
                || currentPage.equals(MOVIES_PAGE)) {
            Application.setCurrentPage(UPGRADES_PAGE);
            action.setErrorOutput(new ErrorOutput());
        } else {
            action.setErrorOutput(new ErrorOutput(HOME_PAGE));
        }
    }

    /**
     * Buy the specified amount of tokens for the current user, using its balance
     * @param action the current action
     */
    public static void buyTokens(final OnPageAction action) {
        User user = new User(Application.getCurrentUser());
        int currentBalance = Integer.parseInt(user.getCredentials().getBalance());
        int countTokens = action.getCount();

        user.getCredentials().setBalance(Integer.toString(currentBalance - countTokens));
        user.setTokensCount(user.getTokensCount() + action.getCount());

        Application.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentUser(user);

    }

    /**
     * Buy a premium account for the current user, using 10 tokens
     * @param action the current action
     */
    public static void buyPremiumAccount(final OnPageAction action) {
        User user = new User(Application.getCurrentUser());

        user.getCredentials().setAccountType("premium");
        user.setTokensCount(user.getTokensCount() - PRICE_FOR_PREMIUM_ACCOUNT);

        Application.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentUser(user);
    }
}
