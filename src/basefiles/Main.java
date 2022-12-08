package basefiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        AppInput appInput = objectMapper.readValue(new File(args[0]), AppInput.class);

        Application application = Application.getInstance();
        List<ErrorOutput> errorsOutput = application.start(appInput);

        ArrayNode output = objectMapper.valueToTree(errorsOutput);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
