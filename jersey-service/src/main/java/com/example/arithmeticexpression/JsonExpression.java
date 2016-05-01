package com.example.arithmeticexpression;

import com.example.grammatic.CalculatorLexer;
import com.example.grammatic.CalculatorParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

/**
 * Created by Admin on 27.04.2016.
 */
public class JsonExpression {

    public JsonExpression() {
    }

    public JSONObject makeJSON(ParseTree parseTree, JSONObject jsonObject) {
        jsonObject.put("expression",parseTree.getText());
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i < parseTree.getChildCount(); i++) {
            if(parseTree.getChild(i).getText()!= "") {
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

    public JSONObject getJsonTreeFromExpression(String expression) throws IOException {
        CalculatorLexer l = new CalculatorLexer(new ANTLRInputStream(expression));
        CalculatorParser p = new CalculatorParser(new CommonTokenStream(l));
        p.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });
        ParseTree parseTree = p.prog().children.get(0).getChild(0);
        JSONObject jsonObject = new JSONObject();
        makeJSON(parseTree,jsonObject);
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
}
