package ru.yandex.core;

import org.apache.log4j.Logger;
import ru.yandex.grammatic.CalculatorLexer;
import ru.yandex.grammatic.CalculatorParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Admin on 27.04.2016.
 */
public class JsonExpressionHandler {

    private final static Logger logger = Logger.getLogger(JsonExpressionHandler.class);
    private static JsonExpressionHandler instance = new JsonExpressionHandler();

    private JsonExpressionHandler() {
    }

    public static JsonExpressionHandler getInstance() {
        if(instance == null) {
            instance = new JsonExpressionHandler();
        }
        return instance;
    }

    private JSONObject makeJSON(ParseTree parseTree, JSONObject jsonObject) {
        jsonObject.put("expression",parseTree.getText());
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < parseTree.getChildCount(); i++) {
            if(!parseTree.getChild(i).getText().equals("")) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("expression", parseTree.getChild(i).getText());
                if(parseTree.getChild(i).getChildCount() != 0)
                    jsonObject1.put("nodes", makeJSON(parseTree.getChild(i), new JSONObject()).get("nodes"));
                jsonArray.put(jsonObject1);
            }
        }
        if(jsonArray.length() != 0)
            jsonObject.put("nodes",jsonArray);
        return jsonObject;
    }

    public JSONObject getJsonTreeFromExpression(String expression) {
        CalculatorLexer l = new CalculatorLexer(new ANTLRInputStream(expression+"\r\n"));
        CalculatorParser p = new CalculatorParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                logger.error("failed to parse at line " + line + " due to " + msg, e);
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        ParseTree parseTree;
        parseTree = p.prog().children.get(0).getChild(0);
        JSONObject jsonObject = new JSONObject();
        makeJSON(parseTree,jsonObject);
        logger.info("json: " + jsonObject.toString());
        return jsonObject;
    }
}
