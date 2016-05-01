package ru.yandex.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.yandex.model.*;
import java.io.IOException;

/**
 * Created by Admin on 30.04.2016.
 */
public class ExpressionNodeDeserializer {

    public ExpressionNodeDeserializer() {

    }

    public ExpressionNode deserialize(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonInString);
        return mapper.treeToValue(rootNode, ExpressionNode.class);
    }
}
