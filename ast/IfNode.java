package ast;

public class IfNode extends ControlSentenceNode {
    protected SentenceNode elseBlock;

    public IfNode(ExpressionNode boolean_expression, BlockNode block, SentenceNode elseBlock) {
        super(boolean_expression, block);
        this.elseBlock = elseBlock;
    }

    public String toString() {
        String s = "If(cond = " + boolean_expression + ", then = " + block;
        if (elseBlock != null)
            s += ", else = " + elseBlock;
        return s + ")";
    }

    @Override
    public void bind() {
        boolean_expression.bind();
        block.bind();
        if (elseBlock != null)
            elseBlock.bind();
    }

    @Override
    public void typeCheck() {
        super.typeCheck();
        if (elseBlock != null)
            elseBlock.typeCheck();
    }
}
