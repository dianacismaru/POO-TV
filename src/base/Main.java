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
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AppInput appInput = objectMapper.readValue(new File(args[0]), AppInput.class);

        List<ErrorOutput> errorsOutput = new ArrayList<>();
        Action.setCurrentPage("homepage");
        Action.setAppInput(appInput);
        Action.setCurrentUser(null);
        Action.setCurrentMoviesList(null);
        Action.setFilteredMovieList(null);

        for (Action action : appInput.getActions()) {
            action.execute();

            if (hasOutput(action)) {
                errorsOutput.add(action.getErrorOutput());
            }
        }

        ArrayNode output = objectMapper.valueToTree(errorsOutput);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
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
}
