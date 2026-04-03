package ast;

public class ReturnNode extends SentenceNode{
    protected ExpressionNode expression;

    public ReturnNode(ExpressionNode expression) {
        this.expression = expression;
    }

    public String toString() {
        return "Return(expr = " + expression + ")";
    }
}
