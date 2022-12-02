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
        Input input = objectMapper.readValue(new File("checker/resources/in/basic_1.json"), Input.class);

        List<ErrorOutput> errorsOutput = new ArrayList<>();
        for (Action action: input.getActions()) {
            errorsOutput.add(new ErrorOutput());
        }

        ArrayNode output = objectMapper.valueToTree(errorsOutput);

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File("results.out"), output);
    }
}
