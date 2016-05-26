package ru.yandex.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 24.05.2016.
 */
public class ExpressionNodeParserTest {
    private static final String testing = "{\"variables\":[\"a\",\"b\",\"c\",\"d\"],\"tree\":{\"expression\":\"(a+b)*(c-d)\",\"nodes\":[{\"expression\":\"(a+b)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"a+b\",\"nodes\":[{\"expression\":\"a\",\"nodes\":[{\"expression\":\"a\"}]},{\"expression\":\"+\"},{\"expression\":\"b\",\"nodes\":[{\"expression\":\"b\"}]}]},{\"expression\":\")\"}]},{\"expression\":\"*\"},{\"expression\":\"(c-d)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"c-d\",\"nodes\":[{\"expression\":\"c\",\"nodes\":[{\"expression\":\"c\"}]},{\"expression\":\"-\"},{\"expression\":\"d\",\"nodes\":[{\"expression\":\"d\"}]}]},{\"expression\":\")\"}]}]}}";
    private static final String resultStructureString = "ResultStructure {'Variables {'{a=null, b=null, c=null, d=null}'}',ExpressionNode {'(a+b)*(c-d)',[ExpressionNode {'(a+b)',[ExpressionNode {'(',null}, ExpressionNode {'a+b',[ExpressionNode {'a',[ExpressionNode {'a',null}]}, ExpressionNode {'+',null}, ExpressionNode {'b',[ExpressionNode {'b',null}]}]}, ExpressionNode {')',null}]}, ExpressionNode {'*',null}, ExpressionNode {'(c-d)',[ExpressionNode {'(',null}, ExpressionNode {'c-d',[ExpressionNode {'c',[ExpressionNode {'c',null}]}, ExpressionNode {'-',null}, ExpressionNode {'d',[ExpressionNode {'d',null}]}]}, ExpressionNode {')',null}]}]}}";

    @Test
    public void testParseJsonToExpressionNode() throws Exception {
        Assert.assertEquals(ResultStructureParser.getInstance().parseJsonToResultStructure(testing).toString(),resultStructureString);
    }
}