package pages;

import actions.Action;
import actions.OnPageAction;
import base.ErrorOutput;
import users.User;

public final class MoviesPage extends Page {
    public static void search(OnPageAction action) {
        if (!Action.getCurrentPage().equals("movies")) {
            action.setErrorOutput(new ErrorOutput("Error"));
            return;
        }

        //action.getStartsWith()
/*        String userName = action.getCredentials().getName();
        String password = action.getCredentials().getPassword();

        for (User user: Action.getAppInput().getUsers()) {
            if (user.getCredentials().getName().equals(userName)
                    && user.getCredentials().getPassword().equals(password)) {
                Action.setCurrentUser(user);
                action.setErrorOutput(new ErrorOutput());
                return;
            }
        }
        action.setErrorOutput(new ErrorOutput("Error"));*/
    }

    public void filter() {

    }
}
