package jsonmapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.MyTreeNode;

import java.io.IOException;

/**
 * Created by Admin on 30.04.2016.
 */
public class JsonDeserializator {

    public JsonDeserializator() {

    }

    public MyTreeNode deserialize(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonInString);
        return mapper.treeToValue(rootNode,MyTreeNode.class);
    }
}
