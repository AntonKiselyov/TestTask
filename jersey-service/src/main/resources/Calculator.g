grammar Calculator;

@header {
    package grammatic;
}

/* Lexical rules */

MULT   : '*' ;
DIV    : '/' ;
PLUS   : '+' ;
MINUS  : '-' ;
UMINUS : '--';

LPAREN : '(' ;
RPAREN : ')' ;

/* Grammar rules */

prog : single_rule+ ;

single_rule : arithmetic_expr NEWLINE
            | NEWLINE
            ;


arithmetic_expr
                :
                arithmetic_expr UMINUS                  # ArithmeticExpressionUMinus
                | arithmetic_expr MULT arithmetic_expr  # ArithmeticExpressionMult
                | arithmetic_expr DIV arithmetic_expr   # ArithmeticExpressionDiv
                | arithmetic_expr PLUS arithmetic_expr  # ArithmeticExpressionPlus
                | arithmetic_expr MINUS arithmetic_expr # ArithmeticExpressionMinus
                | LPAREN arithmetic_expr RPAREN         # ArithmeticExpressionParens
                |                                       # ArithmeticExpressionEntity
                ;

NEWLINE     :    '\r'? '\n';
