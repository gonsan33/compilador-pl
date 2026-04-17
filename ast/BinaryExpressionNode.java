package ast;

import errors.GestionErroresTiny;

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

    @Override
    public void typeCheck() {
        left.typeCheck();
        right.typeCheck();
        switch(this.op) {
            case SUM: 
            case DIFFERENCE: 
            case MULTIPLICATION: 
            case DIVISION:
            case MODULE:
            case LEQ: 
            case LT: 
            case GEQ: 
            case GT: 
            case EQ : 
                if(! this.left.getType().equals( new IntTypeNode()) || ! this.right.getType().equals(new IntTypeNode()) ) new GestionErroresTiny().errorTipos("Error de tipos: ambos operandos deben tener tipo entero");
                break; 
            case AND: 
            case OR: 
                if(! this.left.getType().equals( new BoolTypeNode()) || ! this.right.getType().equals(new BoolTypeNode()) ) new GestionErroresTiny().errorTipos("Error de tipos: ambos operandos deben tener tipo bool");
        }
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

    @Override
    public TypeNode getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }
}
