package ast;

import errors.GestionErroresTiny;

public class AssignNode extends SentenceNode{
    protected DesignatorNode designated;
    protected ExpressionNode expression;

    public AssignNode(DesignatorNode designated, ExpressionNode expression) {
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
    public StringBuilder generateCode() {
        StringBuilder str= new StringBuilder();
        str.append(this.designated.generateCodeL());
        str.append(this.expression.generateCode());
        str.append("i32.store\n");
        return str;
    }
}
