package actions;

import basefiles.ErrorOutput;
import basefiles.input.Filters;
import basefiles.input.Credentials;

public abstract class Action {
    private String type;
    private String page;
    private String feature;
    private Credentials credentials;
    private int count;
    private String startsWith;
    private Filters filters;
    private String movie;
    private int rate;

    private ErrorOutput errorOutput;

    protected static final String INVALID_CASE = "Invalid case!";

    /**
     * Execute an input action, depending on its subclass
     */
    public void execute() {
        Action action = (type.equals("change page")
                         ? new ChangePageAction(this)
                         : new OnPageAction(this));
        action.execute();
        setErrorOutput(action.getErrorOutput());
    }

    /**
     * @return the action's type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type of action to be set
     */
    public void setType(final String type) {
        this.type = type;
    }

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
     * @return the credentials that this action uses
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * @param credentials the credentials to be set
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     * @return the number of tokens that are bought
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the number of tokens to be set
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * @return the string that will be checked for filtering
     */
    public String getStartsWith() {
        return startsWith;
    }

    /**
     * @param startsWith the string to be set for filtering
     */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /**
     * @return the filters that this action will apply
     */
    public Filters getFilters() {
        return filters;
    }

    /**
     * @param filters the filters to be set for this action
     */
    public void setFilters(final Filters filters) {
        this.filters = filters;
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
     * @return the rate that will be given to the current movie
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to be set for the current movie
     */
    public void setRate(final int rate) {
        this.rate = rate;
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
