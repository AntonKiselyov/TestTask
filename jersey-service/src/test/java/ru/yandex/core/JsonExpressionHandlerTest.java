package ru.yandex.core;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 01.05.2016.
 */
public class JsonExpressionHandlerTest {

    private static JsonExpressionHandler jsonExpressionHandler;
    private static final String expression = "(+)";
    @Before
    public void setUp() throws Exception {
        jsonExpressionHandler = JsonExpressionHandler.getInstance();
    }

    @Test
    public void testGetJsonTreeFromExpression() throws Exception {
        assert jsonExpressionHandler.getJsonTreeFromExpression("(+)").toString().equals("{\"expression\":\"(+)\",\"nodes\":[{\"expression\":\"(\"},{\"expression\":\"+\",\"nodes\":[{\"expression\":\"+\"}]},{\"expression\":\")\"}]}");
    }
}