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

    @Override
    public void bind() {
        if (init != null) init.bind();
        if (boolean_expression != null) boolean_expression.bind();
        if (step != null) step.bind();
        block.bind();
    }

    @Override 
    public void typeCheck() {
        init.typeCheck(); 
        super.typeCheck();
        step.typeCheck(); 
        if(!)
    }
}
