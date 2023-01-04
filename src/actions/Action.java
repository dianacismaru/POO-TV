package actions;

import basefiles.ErrorOutput;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChangePageAction.class, name = "change page"),
        @JsonSubTypes.Type(value = OnPageAction.class, name = "on page"),
        @JsonSubTypes.Type(value = DatabaseAction.class, name = "database"),
        @JsonSubTypes.Type(value = BackAction.class, name = "back"),

})
public abstract class Action {
    private String page;
    private String feature;
    private String movie;

    private ErrorOutput errorOutput;

    protected static final String INVALID_CASE = "Invalid case!";

    /**
     * Execute an input action, depending on its subclass
     */
    public abstract void execute();

    /**
     * @return the action's type
     */
    public abstract String getType();

    /**
     * @return the page in which the action will be executed
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page the page to be set
     */
    public void setPage(final String page) {
        this.page = page;
    }

    /**
     * @return the feature that this action will perform
     */
    public String getFeature() {
        return feature;
    }

    /**
     * @param feature the feature to be set
     */
    public void setFeature(final String feature) {
        this.feature = feature;
    }

    /**
     * @return the movie that will be purchased
     */
    public String getMovie() {
        return movie;
    }

    /**
     * @param movie the movie to be set for purchasing
     */
    public void setMovie(final String movie) {
        this.movie = movie;
    }

    /**
     * @return the error output for the current action
     */
    public ErrorOutput getErrorOutput() {
        return errorOutput;
    }

    /**
     *
     * @param errorOutput the error output for the current action
     */
    public void setErrorOutput(final ErrorOutput errorOutput) {
        this.errorOutput = errorOutput;
    }
}
