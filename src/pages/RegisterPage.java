package pages;

import actions.ChangePageAction;
import actions.CommandInvoker;
import actions.OnPageAction;
import basefiles.Application;
import basefiles.ErrorOutput;
import basefiles.input.User;

public final class RegisterPage implements Page {
    @Override
    public void changePage(final ChangePageAction action) {
        String currentPage = Application.getCurrentPage();

        if (currentPage.equals(HOME_PAGE)) {
            Application.setCurrentPage(REGISTER_PAGE);
            action.setErrorOutput(new ErrorOutput());
            return;
        }
        action.setErrorOutput(new ErrorOutput(HOME_PAGE));
    }

    /**
     * Register the specified user into the application's database
     * @param action the current action
     */
    public static void register(final OnPageAction action) {
        if (!Application.getCurrentPage().equals(REGISTER_PAGE)) {
            action.setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        String userName = action.getCredentials().getName();
        for (User user: Application.getAppInput().getUsers()) {
            if (user.getCredentials().getName().equals(userName)) {
                action.setErrorOutput(new ErrorOutput(HOME_PAGE));
                return;
            }
        }

        User user = new User(action.getCredentials());
        Application.getAppInput().getUsers().add(user);
        Application.setCurrentUser(user);
        Application.setCurrentPage(LOGGED_HOME_PAGE);
        CommandInvoker.createHistory();
        CommandInvoker.push(LOGGED_HOME_PAGE);
        action.setErrorOutput(new ErrorOutput());
    }
}
