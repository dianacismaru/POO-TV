package basefiles.filters;

import java.util.List;

public final class Contains {
    private List<String> actors;
    private List<String> genre;
    private List<String> country;

    public List<String> getActors() {
        return actors;
    }

    public void setActors(final List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenre() {
        return genre;
    }

    public List<String> getCountry() {
        return country;
    }

    public void setCountry(final List<String> country) {
        this.country = country;
    }
}
