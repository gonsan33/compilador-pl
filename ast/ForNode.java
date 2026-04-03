package ast;

public class ForNode extends ControlSentenceNode{
    protected ExpressionNode init;
    protected ExpressionNode step;

    public ForNode(ExpressionNode init, ExpressionNode boolean_expression, ExpressionNode step, BlockNode block) {
        super(boolean_expression, block);
        this.init = init;
        this.step = step;
    }

    public String toString() {
        return "For(init = " + init + ", cond = " + boolean_expression + ", step = " + step + ", block = " + block + ")";
    }
}
