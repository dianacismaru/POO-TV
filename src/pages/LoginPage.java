package pages;

import actions.ChangePageAction;
import actions.CommandInvoker;
import actions.OnPageAction;
import basefiles.Application;
import basefiles.ErrorOutput;
import basefiles.input.User;

public final class LoginPage implements Page {
    @Override
    public void changePage(final ChangePageAction action) {
        String currentPage = Application.getCurrentPage();

        if (currentPage.equals(HOME_PAGE)) {
            Application.setCurrentPage(LOGIN_PAGE);
            action.setErrorOutput(new ErrorOutput());
            return;
        }
        action.setErrorOutput(new ErrorOutput(currentPage));
    }

    /**
     * Login the specified user into the application
     * @param action the current action
     */
    public static void login(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(LOGIN_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        String userName = action.getCredentials().getName();
        String password = action.getCredentials().getPassword();

        for (User user: Application.getAppInput().getUsers()) {
            if (user.getCredentials().getName().equals(userName)
                    && user.getCredentials().getPassword().equals(password)) {
                Application.setCurrentUser(user);
                Application.setCurrentPage(LOGGED_HOME_PAGE);
                CommandInvoker.createHistory();
                CommandInvoker.push(LOGGED_HOME_PAGE);
                action.setErrorOutput(new ErrorOutput());
                return;
            }
        }
        action.setErrorOutput(new ErrorOutput(HOME_PAGE));
    }
}
