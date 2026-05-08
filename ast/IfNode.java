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

    @Override
    public void setFunction(FunctionNode function) {
        super.setFunction(function);
        if (elseBlock instanceof ReturnNode) {
            ((ReturnNode) elseBlock).setFunction(function);
        } else if (elseBlock instanceof BlockNode) {
            ((BlockNode) elseBlock).setFunction(function);
        } else if (elseBlock instanceof ControlSentenceNode) {
            ((ControlSentenceNode) elseBlock).setFunction(function);
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        str.append(this.boolean_expression.generateCode());
        str.append("if");
        str.append(this.block.generateCode());
        if (elseBlock != null) {
            str.append("else \n");
            str.append(elseBlock.generateCode());
        }
        str.append("end \n");
        return str;
    }
}
