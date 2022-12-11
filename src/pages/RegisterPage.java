package pages;

import actions.Action;
import actions.ChangePageAction;
import actions.OnPageAction;
import basefiles.ErrorOutput;
import basefiles.input.User;

public final class RegisterPage extends Page {
    @Override
    public void changePage(final ChangePageAction action) {
        String currentPage = Action.getCurrentPage();

        if (currentPage.equals(HOME_PAGE)) {
            Action.setCurrentPage(REGISTER_PAGE);
            return;
        }
        action.setErrorOutput(new ErrorOutput(HOME_PAGE));
    }

    /**
     * Register the specified user into the application's database
     * @param action the current action
     */
    public static void register(final OnPageAction action) {
        if (!Action.getCurrentPage().equals(REGISTER_PAGE)) {
            action.setErrorOutput(new ErrorOutput(HOME_PAGE));
            return;
        }

        String userName = action.getCredentials().getName();
        for (User user: Action.getAppInput().getUsers()) {
            if (user.getCredentials().getName().equals(userName)) {
                action.setErrorOutput(new ErrorOutput(HOME_PAGE));
                return;
            }
        }

        User user = new User(action.getCredentials());
        Action.getAppInput().getUsers().add(user);
        Action.setCurrentUser(user);
        Action.setCurrentPage(LOGGED_HOME_PAGE);
        action.setErrorOutput(new ErrorOutput());
    }
}
