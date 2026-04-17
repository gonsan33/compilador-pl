package ast;

import errors.GestionErroresTiny;

public class ControlSentenceNode extends SentenceNode {
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
        if (!boolean_expression.getType().equals(new BoolTypeNode()))
            new GestionErroresTiny().errorTipos("La expresión debe ser booleana");
    }

    @Override
    public void generateCode() {
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
