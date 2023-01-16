import core.Application;
import core.ErrorOutput;
import core.input.AppInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class Main {
    private Main() {

    }

    /**
     * @param args the String array that contains the input and the output files
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AppInput appInput = objectMapper.readValue(new File(args[0]), AppInput.class);

        Application application = Application.getInstance();
        Application.setAppInput(appInput);
        List<ErrorOutput> errorsOutput = application.start();

        ArrayNode output = objectMapper.valueToTree(errorsOutput);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
