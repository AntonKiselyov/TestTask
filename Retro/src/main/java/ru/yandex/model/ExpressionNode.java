package ru.yandex.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Admin on 29.04.2016.
 */
public class ExpressionNode {
    private String expression;
    private List<ExpressionNode> nodes;

    @JsonCreator
    public ExpressionNode(@JsonProperty("expression") String expression, @JsonProperty("nodes") List<ExpressionNode> nodes) {
        this.expression = expression;
        this.nodes = nodes;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setNodes(List<ExpressionNode> nodes) {
        this.nodes = nodes;
    }

    public List<ExpressionNode> getNodes() {
        return nodes;
    }

    public void add(ExpressionNode expressionNode) {
        this.nodes.add(expressionNode);
    }

    @Override
    public String toString() {
        return "ExpressionNode {\'" + expression + "\'," + nodes + "}";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(getClass() == obj.getClass()))
            return false;
        else
        {
            ExpressionNode tmp = (ExpressionNode) obj;
            if( (tmp.expression.equals(this.expression)) ) {
                if ( (tmp.nodes != null) && (this.nodes != null) )  {
                    return tmp.nodes.equals(this.nodes);
                }
                else return (tmp.nodes == null) && (this.nodes == null);
            }
            else return false;
        }
    }

}
