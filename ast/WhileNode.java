package ast;

public class WhileNode extends ControlSentenceNode{

    public WhileNode(ExpressionNode boolean_expression, BlockNode block) {
        super(boolean_expression, block);
    }

    public String toString() {
        return "While(cond = " + boolean_expression + ", block = " + block + ")";
    }
}
