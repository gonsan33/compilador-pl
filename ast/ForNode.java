package ast;

public class ForNode extends ControlSentenceNode {
    protected ExpressionNode init;
    protected ExpressionNode step;

    public ForNode(ExpressionNode init, ExpressionNode boolean_expression, ExpressionNode step, BlockNode block) {
        super(boolean_expression, block);
        this.init = init;
        this.step = step;
    }

    public String toString() {
        return "For(init = " + init + ", cond = " + boolean_expression + ", step = " + step + ", block = " + block
                + ")";
    }

    @Override
    public void bind() {
        if (init != null)
            init.bind();
        if (boolean_expression != null)
            boolean_expression.bind();
        if (step != null)
            step.bind();
        block.bind();
    }

    @Override
    public void typeCheck() {
        if (init != null)
            init.typeCheck();
        super.typeCheck();
        if (step != null)
            step.typeCheck();
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        str.append(init.generateCode());    
        str.append("block\n");
        str.append("loop\n");
        str.append(boolean_expression.generateCode());
        str.append("i32.eqz\n");
        str.append("br_if 1\n");
        str.append(this.block.generateCode());
        str.append(step.generateCode());
        str.append("br 0 \n");
        str.append("end \n");
        str.append("end \n");
        return str; 
    }
}
