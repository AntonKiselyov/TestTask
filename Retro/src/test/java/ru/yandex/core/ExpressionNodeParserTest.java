package ru.yandex.core;

import org.junit.Test;

/**
 * Created by Admin on 24.05.2016.
 */
public class ExpressionNodeParserTest {
    private static final String testing = "{\"expression\":\"(+)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"+\",\"nodes\":[{\"expression\":\"+\"}]},{\"expression\":\")\"}]}";
    private static final String expressionNodeString = "ExpressionNode {'(+)',[ExpressionNode {'(',null}, ExpressionNode {'+',[ExpressionNode {'+',null}]}, ExpressionNode {')',null}]}";

    @Test
    public void testParseJsonToExpressionNode() throws Exception {
        assert  ExpressionNodeParser.getInstance().parseJsonToExpressionNode(testing).toString().equals(expressionNodeString);
    }
}