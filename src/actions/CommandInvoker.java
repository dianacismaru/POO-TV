package actions;

public final class CommandInvoker {
    /**
     * Execute the given action
     * @param action the action to be executed
     */
    public void executeCommand(final Action action) {
        action.execute();
    }
}
