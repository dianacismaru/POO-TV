package actions;

import basefiles.Application;
import pages.Page;
import pages.PageFactory;


public final class ChangePageAction extends Action {
    @Override
    public void execute() {
        Page page = PageFactory.createPage(this.getPage());
        page.changePage(this);

        if (Application.getCurrentUser() != null && this.getErrorOutput().getError() == null) {
            CommandInvoker.push(Application.getCurrentPage());
        }
    }

    @Override
    public String getType() {
        return "change page";
    }
}
