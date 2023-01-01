package actions;

public final class SubscribeAction extends Action {
    private String subscribedGenre;

    @Override
    public void execute() {

    }

    @Override
    public String getType() {
        return "subscribe";
    }

    public String getSubscribedGenre() {
        return subscribedGenre;
    }

    public void setSubscribedGenre(final String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }
}
