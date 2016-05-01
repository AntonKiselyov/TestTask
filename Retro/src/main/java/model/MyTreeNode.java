package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Admin on 29.04.2016.
 */
public class MyTreeNode {
    private String expression;
    private List<MyTreeNode> nodes;

    public MyTreeNode() {

    }

    @JsonCreator
    public MyTreeNode(@JsonProperty("expression") String expression, @JsonProperty("nodes") List<MyTreeNode> nodes) {
        this.expression = expression;
        this.nodes = nodes;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public void setNodes(List<MyTreeNode> nodes) {
        this.nodes = nodes;
    }

    public List<MyTreeNode> getNodes() {
        return nodes;
    }

    public void addMyTreeNodes(MyTreeNode myTreeNode) {
        this.nodes.add(myTreeNode);
    }

    @Override
    public String toString() {
        return "MyTreeNode {\'" + expression + "\'," + nodes + "}";
    }
}
