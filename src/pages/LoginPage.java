package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import basefiles.ErrorOutput;
import users.User;

public final class LoginPage extends Page {
    @Override
    public void changePage(ChangePageAction action) {
        String currentPage = Action.getCurrentPage();

        if (currentPage.equals(HOME_PAGE)) {
            Action.setCurrentPage(LOGIN_PAGE);
            return;
        }
        action.setErrorOutput(new ErrorOutput(currentPage));
    }

    public static void login(final OnPageAction action) {
        if (!Action.getCurrentPage().equals(LOGIN_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        String userName = action.getCredentials().getName();
        String password = action.getCredentials().getPassword();

        for (User user: Action.getAppInput().getUsers()) {
            if (user.getCredentials().getName().equals(userName)
                    && user.getCredentials().getPassword().equals(password)) {
                Action.setCurrentUser(user);
                Action.setCurrentPage(LOGGED_HOME_PAGE);
                action.setErrorOutput(new ErrorOutput());
                return;
            }
        }
        action.setErrorOutput(new ErrorOutput(HOME_PAGE));
    }
}
