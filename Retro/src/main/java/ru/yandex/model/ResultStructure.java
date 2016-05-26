package ru.yandex.model;

/**
 * Created by Admin on 27.05.2016.
 */
public class ResultStructure {
    private ExpressionNode expressionNode;
    private Variables variables;

    public ResultStructure(ExpressionNode expressionNode, Variables variables) {
        this.expressionNode = expressionNode;
        this.variables = variables;
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }

    public Variables getVariables() {
        return variables;
    }


    @Override
    public String toString() {
        return "ResultStructure {\'" + variables + "\'," + expressionNode + "}";
    }
}
