package ast;

public abstract class DesignatorNode extends ExpressionNode {
    protected int delta; 
    public abstract int delta(); 
}
