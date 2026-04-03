package ast;

public class FieldAccessNode extends ExpressionNode{
    protected ExpressionNode designator;
    protected String field;

    public FieldAccessNode(ExpressionNode designator, String field) {
        this.designator = designator;
        this.field = field;
    }

    public String toString() {
        return "FieldAccess(designator = " + designator + ", field = " + field + ")";
    }
}
