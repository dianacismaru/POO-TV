package actions;

import pages.Page;
import pages.PageFactory;


public final class ChangePageAction extends Action {
    @Override
    public void execute() {
        Page page = PageFactory.createPage(this.getPage());
        page.changePage(this);
    }

    @Override
    public String getType() {
        return "change page";
    }
}
