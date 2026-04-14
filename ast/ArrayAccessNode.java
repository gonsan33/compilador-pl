package ast;

public class ArrayAccessNode extends DesignatorNode{
    protected ExpressionNode designator;
    protected ExpressionNode index;

    public ArrayAccessNode(ExpressionNode designator, ExpressionNode index) {
        this.designator = designator;
        this.index = index;
    }

    public String toString() {
        return "ArrayAccess(designator = " + designator + ", index = " + index + ")";
    }

    @Override
    public void bind() {
        designator.bind();
        index.bind();
    }
}
