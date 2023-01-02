package actions;

import basefiles.Application;
import basefiles.ErrorOutput;
import pages.Page;
import pages.PageFactory;

import static pages.Page.*;

public final class BackAction extends Action {
    @Override
    public void execute() {
        if (CommandInvoker.size() <= 1 || Application.getCurrentUser() == null) {
            //System.out.println("NU a mers sa ma intorc!");
            setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        CommandInvoker.pop();
        String previousPage = CommandInvoker.peek();

        //System.out.println("pagina pe care ma voi intoarce este: " + previousPage);

        if (previousPage.equals(LOGIN_PAGE) || previousPage.equals(REGISTER_PAGE)
                || previousPage.equals(HOME_PAGE)) {
            setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            //System.out.println("NU a mers sa ma intorc!");
            return;
        }

       // System.out.println("a mers sa ma intorc!");

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
