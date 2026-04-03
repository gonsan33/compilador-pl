package ast;

public class ControlSentenceNode extends SentenceNode{
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
        if (boolean_expression != null) boolean_expression.bind();
        if (block != null) block.bind();
    }

    @Override
    public void typeCheck() {
        throw new UnsupportedOperationException("Unimplemented method 'typeCheck'");
    }

    @Override
    public void generateCode() {
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
