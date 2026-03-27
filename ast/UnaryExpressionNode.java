package ast;

import java.util.function.UnaryOperator;

public class UnaryExpressionNode {
    protected ExpressionNode exp; 
    protected UnaryOperation op; 
    
    public UnaryExpressionNode(UnaryOperation op, ExpressionNode exp) {
        this.op = op; 
        this.exp = exp; 
    }

    public String toString() {
        return op.toString() + "(exp = " + exp.toString() + ")"; 
    }
}
