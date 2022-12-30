package actions;

import basefiles.ErrorOutput;
import pages.Page;
import pages.PageFactory;


public final class ChangePageAction extends Action {
    public ChangePageAction(final Action action) {
        this.setType(action.getType());
        this.setPage(action.getPage());
        this.setMovie(action.getMovie());
        this.setErrorOutput(new ErrorOutput());
    }

    @Override
    public void execute() {
        Page page = PageFactory.createPage(this.getPage());
        page.changePage(this);
    }
}
