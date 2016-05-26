package ru.yandex.core;

import ru.yandex.model.ExpressionNode;
import ru.yandex.model.Variables;

/**
 * Created by Admin on 26.05.2016.
 */
public class ExpressionCalculator {
    private static ExpressionCalculator calculator;
    private ExpressionCalculator() {

    }
    public static ExpressionCalculator getCalculator() {
        if(calculator == null) {
            calculator = new ExpressionCalculator();
        }
        return calculator;
    }

    public double calculate(ExpressionNode expressionNode, Variables variables) {
        double result = 0;
        if(expressionNode.getNodes().size() == 1) {
            result = variables.getVars().get(expressionNode.getExpression());
        } else {
            switch (expressionNode.getNodes().get(1).getExpression()) {
                case "+":
                    result = calculate(expressionNode.getNodes().get(0), variables) + calculate(expressionNode.getNodes().get(2), variables);
                    break;
                case "-":
                    result = calculate(expressionNode.getNodes().get(0), variables) - calculate(expressionNode.getNodes().get(2), variables);
                    break;
                case "*":
                    result = calculate(expressionNode.getNodes().get(0), variables) * calculate(expressionNode.getNodes().get(2), variables);
                    break;
                case "/":
                    result = calculate(expressionNode.getNodes().get(0), variables) / calculate(expressionNode.getNodes().get(2), variables);
                    break;
                case "--":
                    result = calculate(expressionNode.getNodes().get(0), variables) - 1;
                    break;
                case "(":
                    break;
                case ")":
                    break;
                default:
                    result = calculate(expressionNode.getNodes().get(1), variables);
                    break;
            }
        }
        return result;
    }
}
