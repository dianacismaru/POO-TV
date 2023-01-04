package actions;

import basefiles.Application;
import basefiles.ErrorOutput;
import pages.Page;
import pages.PageFactory;

import static pages.Page.LOGIN_PAGE;
import static pages.Page.REGISTER_PAGE;
import static pages.Page.HOME_PAGE;
import static pages.Page.LOGGED_HOME_PAGE;

public final class BackAction extends Action {
    @Override
    public void execute() {
        if (CommandInvoker.size() <= 1 || Application.getCurrentUser() == null) {
            setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        CommandInvoker.pop();
        String previousPage = CommandInvoker.peek();

        if (previousPage.equals(LOGIN_PAGE) || previousPage.equals(REGISTER_PAGE)
                || previousPage.equals(HOME_PAGE)) {
            setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        if (previousPage.equals(LOGGED_HOME_PAGE)) {
            Application.setCurrentPage(LOGGED_HOME_PAGE);
            setErrorOutput(new ErrorOutput());
            return;
        }

        Page page = PageFactory.createPage(previousPage);
        page.changePage(new ChangePageAction());
        setErrorOutput(new ErrorOutput());
    }

    @Override
    public String getType() {
        return "back";
    }
}
