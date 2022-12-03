package pages;

import actions.Action;
import actions.OnPageAction;
import base.ErrorOutput;
import users.User;

public final class RegisterPage extends Page {
    public static void register(OnPageAction action) {
        if (!Action.getCurrentPage().equals("register")) {
            action.setErrorOutput(new ErrorOutput("homepage"));
            return;
        }

        String userName = action.getCredentials().getName();
        for (User user: Action.getAppInput().getUsers()) {
            if (user.getCredentials().getName().equals(userName)) {
                action.setErrorOutput(new ErrorOutput("homepage"));
                return;
            }
        }

        User user = new User(action.getCredentials());
        Action.getAppInput().getUsers().add(user);
        Action.setCurrentUser(user);
        Action.setCurrentPage("loggedHomepage");
        action.setErrorOutput(new ErrorOutput());
    }
}