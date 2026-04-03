package ast;

public class AssignNode extends SentenceNode{
    protected ExpressionNode designator;
    protected ExpressionNode expression;

    public AssignNode(ExpressionNode designator, ExpressionNode expression) {
        this.designator = designator;
        this.expression = expression;
    }

    public String toString() {
        return "Assign(designator = " + designator + ", expression = " + expression + ")";
    }
}
