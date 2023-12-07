package lox;

// tip: Ast means Abstract Syntax Tree
public class AstPrinterRPN implements Expr.Visitor<String>{
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("grouping", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if(expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        System.out.println("THATS MY NAME  "+name);
        StringBuilder builder = new StringBuilder();
        for(Expr expr : exprs) {
            System.out.print("A SINGLE EXPRESSION: ");
            System.out.println(new AstPrinter().print(expr));
            builder.append(expr.accept(this));
            builder.append(" ");
        }
        builder.append(name);
        return builder.toString();
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Binary(
                        new Expr.Literal(1),
                        new Token(TokenType.PLUS, "+", null, 1),
                        new Expr.Literal(2)
                ),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Binary(
                        new Expr.Literal(4),
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(3)
                )
        );

        Expr expressionOld = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)
                ),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Literal(4200.69)
        );
        System.out.println(new AstPrinterRPN().print(expression));
        // unary expression (Token.MINUS and a literal token 123)
        // a star token (*) that for some reason is at the beginning
        // a literal at the end: 4200.69
        // (* (- 123) 4200.69) => ((123) - 4200.69)*)
    }
}
