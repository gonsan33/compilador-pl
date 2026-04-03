package ast;

public class UnaryExpressionNode extends ExpressionNode {
    protected ExpressionNode exp; 
    protected UnaryOperation op; 
    
    public UnaryExpressionNode(UnaryOperation op, ExpressionNode exp) {
        this.op = op; 
        this.exp = exp; 
    }

    public String toString() {
        return op.toString() + "(exp = " + exp.toString() + ")"; 
    }

    @Override
    public void bind() {
        exp.bind();
    }
}
