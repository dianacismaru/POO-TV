package pages;

import actions.Action;
import actions.OnPageAction;
import base.ErrorOutput;
import users.User;

public final class LoginPage extends Page {
    public static void login(OnPageAction action) {
        if (!Action.getCurrentPage().equals("login")) {
            action.setErrorOutput(new ErrorOutput(Action.getCurrentPage()));
            return;
        }

        String userName = action.getCredentials().getName();
        String password = action.getCredentials().getPassword();

        for (User user: Action.getAppInput().getUsers()) {
            if (user.getCredentials().getName().equals(userName)
                    && user.getCredentials().getPassword().equals(password)) {
                Action.setCurrentUser(user);
                Action.setCurrentPage("loggedHomepage");
                action.setErrorOutput(new ErrorOutput());
                return;
            }
        }
        action.setErrorOutput(new ErrorOutput("homepage"));
    }
}
