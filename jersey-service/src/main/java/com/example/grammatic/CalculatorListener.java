// Generated from Calculator.g by ANTLR 4.5.3

    package com.example.grammatic;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CalculatorParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CalculatorParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#single_rule}.
	 * @param ctx the parse tree
	 */
	void enterSingle_rule(CalculatorParser.Single_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#single_rule}.
	 * @param ctx the parse tree
	 */
	void exitSingle_rule(CalculatorParser.Single_ruleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionMult}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionMult(CalculatorParser.ArithmeticExpressionMultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionMult}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionMult(CalculatorParser.ArithmeticExpressionMultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionEntity}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionEntity(CalculatorParser.ArithmeticExpressionEntityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionEntity}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionEntity(CalculatorParser.ArithmeticExpressionEntityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionUMinus}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionUMinus(CalculatorParser.ArithmeticExpressionUMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionUMinus}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionUMinus(CalculatorParser.ArithmeticExpressionUMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionMinus}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionMinus(CalculatorParser.ArithmeticExpressionMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionMinus}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionMinus(CalculatorParser.ArithmeticExpressionMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionParens}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionParens(CalculatorParser.ArithmeticExpressionParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionParens}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionParens(CalculatorParser.ArithmeticExpressionParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionDiv}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionDiv(CalculatorParser.ArithmeticExpressionDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionDiv}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionDiv(CalculatorParser.ArithmeticExpressionDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionPlus}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionPlus(CalculatorParser.ArithmeticExpressionPlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionPlus}
	 * labeled alternative in {@link CalculatorParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionPlus(CalculatorParser.ArithmeticExpressionPlusContext ctx);
}