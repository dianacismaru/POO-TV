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

    public static void createHistory() {
        history = new ArrayList<>();
    }

    public static void deleteHistory() {
        history = null;
    }

    public static void push(String page) {
        history.add(page);
    }

    public static void pop() {
        history.remove(history.size() - 1);
    }

    public static String peek() {
        return history.get(history.size() - 1);
    }

    public static int size() {
        if (history != null) {
            return history.size();
        }
        return 0;
    }

    public static void printHistory() {
        if (history == null) {
            System.out.println("[ COADA E GOALA ]");
            return;
        }
        System.out.print("[");
        for (String string: history) {
            System.out.printf("%s, ", string);
        }
        System.out.println("]");
    }
}
