package actions;

import basefiles.Application;
import basefiles.ErrorOutput;
import basefiles.input.Movie;
import basefiles.observer.Observer;
import basefiles.observer.Subject;

import static pages.Page.SEE_DETAILS_PAGE;

public final class SubscribeAction extends Action {
    private String subscribedGenre;

    @Override
    public void execute() {
        if (!Application.getCurrentPage().equals(SEE_DETAILS_PAGE)) {
            setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        Movie currentMovie = Application.getCurrentMoviesList().get(0);

        if (!currentMovie.getGenres().contains(subscribedGenre)) {
            setErrorOutput(new ErrorOutput(Application.getCurrentPage()));
            return;
        }

        // TODO: daca se vrea abonarea la un gen la care utilizatorul e deja abonat -> eroare
        // TODO: subscribe-ul se efectueaza cu succes
        // grija la tarile banate



        setErrorOutput(new ErrorOutput());
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
