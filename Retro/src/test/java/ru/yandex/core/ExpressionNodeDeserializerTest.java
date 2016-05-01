package ru.yandex.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Admin on 01.05.2016.
 */
public class ExpressionNodeDeserializerTest {

    private static final String testing = "{\"expression\":\"(+)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"+\",\"nodes\":[{\"expression\":\"+\"}]},{\"expression\":\")\"}]}";
    private static String expressionNodeString = "ExpressionNode {'(+)',[ExpressionNode {'(',null}, ExpressionNode {'+',[ExpressionNode {'+',null}]}, ExpressionNode {')',null}]}";


    @Test
    public void testDeserialize() throws Exception {
        ExpressionNodeDeserializer.getInstance().deserialize(testing).equals(expressionNodeString);
    }
}