package ast;

import errors.GestionErroresTiny;

public abstract class ControlSentenceNode extends SentenceNode {
    protected ExpressionNode boolean_expression;
    protected BlockNode block;

    public ControlSentenceNode(ExpressionNode boolean_expression, BlockNode block) {
        this.boolean_expression = boolean_expression;
        this.block = block;
    }

    public String toString() {
        return "ControlSentence(cond = " + boolean_expression + ", block = " + block + ")";
    }

    @Override
    public void bind() {
        if (boolean_expression != null)
            boolean_expression.bind();
        if (block != null)
            block.bind();
    }

    @Override
    public void typeCheck() {
        this.boolean_expression.typeCheck();
        this.block.typeCheck();
        if (!boolean_expression.getType().equals(new BoolTypeNode()))
            new GestionErroresTiny().errorTipos("La expresión debe ser booleana");
    }

    public void setFunction(FunctionNode function) {
        if (block != null) {
            block.setFunction(function);
        }
    }

    public abstract  StringBuilder generateCode(); 
}
