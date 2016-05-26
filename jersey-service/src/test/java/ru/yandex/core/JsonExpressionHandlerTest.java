package ru.yandex.core;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 01.05.2016.
 */
public class JsonExpressionHandlerTest {

    private static JsonExpressionHandler jsonExpressionHandler;
    @Before
    public void setUp() throws Exception {
        jsonExpressionHandler = JsonExpressionHandler.getInstance();
    }

    @Test
    public void testGetJsonTreeFromExpression() throws Exception {
        Assert.assertEquals(jsonExpressionHandler.getJsonTreeFromExpression("a+b").toString(),"{\"variables\":[\"a\",\"b\"],\"tree\":{\"expression\":\"a+b\",\"nodes\":[{\"expression\":\"a\",\"nodes\":[{\"expression\":\"a\"}]},{\"expression\":\"+\"},{\"expression\":\"b\",\"nodes\":[{\"expression\":\"b\"}]}]}}");
    }
}