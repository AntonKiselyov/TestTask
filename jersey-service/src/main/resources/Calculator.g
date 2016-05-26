grammar Calculator;

@header {
    package ru.yandex.grammatic;
    import java.util.Set;
    import java.util.HashSet;
}

@members{
	Set<String> variables = new HashSet<>();
}
/* Lexical rules */

MULT   : '*' ;
DIV    : '/' ;
PLUS   : '+' ;
MINUS  : '-' ;
UMINUS : '--';

LPAREN : '(' ;
RPAREN : ')' ;

VARIABLE  :  ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*  ;

/* Grammar rules */

prog : single_rule+ ;

single_rule : arithmetic_expr NEWLINE
            | NEWLINE
            ;

arithmetic_expr
                :
                  VARIABLE     { variables.add((String)$VARIABLE.text); }                      # ArithmeticExpressionEntity
                | arithmetic_expr UMINUS                  # ArithmeticExpressionUMinus
                | arithmetic_expr MULT arithmetic_expr  # ArithmeticExpressionMult
                | arithmetic_expr DIV arithmetic_expr   # ArithmeticExpressionDiv
                | arithmetic_expr PLUS arithmetic_expr  # ArithmeticExpressionPlus
                | arithmetic_expr MINUS arithmetic_expr # ArithmeticExpressionMinus
                | LPAREN arithmetic_expr RPAREN         # ArithmeticExpressionParens
                ;

NEWLINE     :    '\r'? '\n';
