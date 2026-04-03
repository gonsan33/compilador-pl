package ast;

public class PrintNode extends SentenceNode{
    protected ExpressionNode expression;

    public PrintNode(ExpressionNode expression) {
        this.expression = expression;
    }

    public String toString() {
        return "Print(expr = " + expression + ")";
    }
}
