package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import base.ErrorOutput;
import users.User;

public class UpgradesPage extends Page {
    public static void upgrades(ChangePageAction action) {
        String currentPage = Action.getCurrentPage();

        if (currentPage.equals("loggedHomepage")
                || currentPage.equals("see details")) {
            Action.setCurrentPage("upgrades");
        } else {
            action.setErrorOutput(new ErrorOutput("homepage"));
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
        user.setTokensCount(user.getTokensCount() - 10);

        Action.setCurrentUser(user);
        action.setErrorOutput(new ErrorOutput());
        action.getErrorOutput().setCurrentUser(user);
    }
}
