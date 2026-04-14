package ast;

public class PointerAccessNode extends DesignatorNode {
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
