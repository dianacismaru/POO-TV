package base;

import actions.Action;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int ct = 0;

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // TESTARE PARTICULARA
        //AppInput appInput = objectMapper.readValue(new File("checker/resources/in/my_test.json"), AppInput.class);

        // TESTARE COMPLETA
        AppInput appInput = objectMapper.readValue(new File(args[0]), AppInput.class);

        List<ErrorOutput> errorsOutput = new ArrayList<>();
        Action.setCurrentPage("homepage");
        Action.setAppInput(appInput);
        Action.setCurrentUser(null);
        Action.setCurrentMoviesList(null);

        for (Action action : appInput.getActions()) {
            action.execute();

            //debuggingMethod(action);

            if (hasOutput(action)) {
                errorsOutput.add(action.getErrorOutput());
            }
        }

        ArrayNode output = objectMapper.valueToTree(errorsOutput);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File("results.out"), output);
    }

    private static boolean hasOutput(Action action) {
        ErrorOutput errorOutput = action.getErrorOutput();

        if (action.getType().equals("on page")
                && errorOutput.getError() == null
                && (action.getFeature().equals("filter")
                || action.getFeature().equals("search")
                || action.getFeature().equals("login")
                || action.getFeature().equals("register"))) {
            return true;
        }

        if (errorOutput.getError() == null
                && (action.getPage().equals("movies")
                || action.getPage().equals("see details"))) {
            return true;
        }

        return errorOutput.getError() != null;
    }

    private static void debuggingMethod(Action action) {
        System.out.println("\ncomanda " + ++ct);
        System.out.println("efectuez actiunea " + action.getType() + " " + action.getPage());

        if (action.getType().equals("on page")) {
            System.out.println("cu feature: " + action.getFeature());
        }

        System.out.println("si am ajuns in pagina " + Action.getCurrentPage());

        if (Action.getCurrentUser() != null)
            System.out.println("utilizator curent: " + Action.getCurrentUser().getCredentials().getName());

        if (action.getErrorOutput().getError() != null)
            System.out.println(action.getErrorOutput().getError());
    }
}
