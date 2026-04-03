package ast;

public class ArrayAccessNode extends ExpressionNode{
    protected ExpressionNode designator;
    protected ExpressionNode index;

    public ArrayAccessNode(ExpressionNode designator, ExpressionNode index) {
        this.designator = designator;
        this.index = index;
    }

    public String toString() {
        return "ArrayAccess(designator = " + designator + ", index = " + index + ")";
    }
}
