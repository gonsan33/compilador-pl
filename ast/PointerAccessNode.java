package ast;

public class PointerAccessNode extends ExpressionNode {
    protected ExpressionNode designator;

    public PointerAccessNode(ExpressionNode designator) {
        this.designator = designator;
    }

    public String toString() {
        return "PointerAccess(designator = " + designator + ")";
    }

    @Override
    public void bind() {
        designator.bind();
    }
}
