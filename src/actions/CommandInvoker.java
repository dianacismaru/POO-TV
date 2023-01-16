package actions;

import java.util.ArrayList;
import java.util.List;

public final class CommandInvoker {
    private static List<String> history;

    /**
     * Execute the given action
     * @param action the action to be executed
     */
    public void executeCommand(final Action action) {
        action.execute();
    }

    /**
     * Initialize the history of pages
     */
    public static void createHistory() {
        history = new ArrayList<>();
    }

    /**
     * Delete the history of pages
     */
    public static void deleteHistory() {
        history = null;
    }

    /**
     * Add a page in top of the stack of history pages
     * @param page the page to be added
     */
    public static void push(final String page) {
        history.add(page);
    }

    /**
     * Remove the last page from the stack
     */
    public static void pop() {
        history.remove(history.size() - 1);
    }

    /**
     * Get the top page on the stack
     */
    public static String peek() {
        return history.get(history.size() - 1);
    }

    /**
     * Get the size of the stack
     * @return the size of the stack
     */
    public static int size() {
        if (history != null) {
            return history.size();
        }
        return 0;
    }
}
