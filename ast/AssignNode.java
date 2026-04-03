package ast;

public class AssignNode extends SentenceNode{
    protected ExpressionNode designated;
    protected ExpressionNode expression;

    public AssignNode(ExpressionNode designated, ExpressionNode expression) {
        this.designated = designated;
        this.expression = expression;
    }

    public String toString() {
        return "Assign(designator = " + designated + ", expression = " + expression + ")";
    }

    @Override
    public void bind() {
        designated.bind();
        expression.bind();
    }
}
