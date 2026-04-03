package ast;

public class BinaryExpressionNode extends ExpressionNode {
    protected ExpressionNode left; 
    protected ExpressionNode right; 
    protected BinaryOperation op; 

    public BinaryExpressionNode(BinaryOperation op, ExpressionNode left, ExpressionNode right) {
        this.op = op; 
        this.left = left; 
        this.right= right; 
    }

    public String toString() {
        return op.toString() + "(left = " +  left.toString() + ", right = " + right.toString() + ")"; 
    }

    @Override
    public void bind() {
        left.bind();
        right.bind();
    }
}
