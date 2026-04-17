package ast;

import errors.GestionErroresTiny;

public class AssignNode extends SentenceNode{
    protected ExpressionNode designated;
    protected ExpressionNode expression;

    public AssignNode(ExpressionNode designated, ExpressionNode expression) {
        this.designated = designated;
        this.expression = expression;
    }

    public String toString() {
        return "Assign(designator = " + designated + ", expression = " + expression + ")";
    }

    @Override
    public void bind() {
        designated.bind();
        expression.bind();
    }

    @Override
    public void typeCheck() {
        designated.typeCheck();
        expression.typeCheck();
        if(! designated.getType().equals(expression.getType())) new GestionErroresTiny().errorTipos("Error de tipos: la expresión y el designador deben tener el mismo tipo");
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
