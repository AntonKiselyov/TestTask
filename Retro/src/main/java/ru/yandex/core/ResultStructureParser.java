package ru.yandex.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.yandex.model.ExpressionNode;
import ru.yandex.model.ResultStructure;
import ru.yandex.model.Variables;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 30.04.2016.
 */
public class ResultStructureParser {

    private static ResultStructureParser instance = new ResultStructureParser();

    private ResultStructureParser() {

    }

    public static ResultStructureParser getInstance() {
        if(instance == null) {
            instance = new ResultStructureParser();
        }
        return instance;
    }

    public ResultStructure parseJsonToResultStructure(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonInString);
        JsonNode treeNode = rootNode.get("tree");
        JsonNode varNode = rootNode.get("variables");
        Set<String> set = mapper.readValue(String.valueOf(varNode),new TypeReference<HashSet<String>>() {});
        ExpressionNode expressionNode = mapper.treeToValue(treeNode, ExpressionNode.class);
        Variables variables = new Variables(set);
        return new ResultStructure(expressionNode,variables);
    }
}
