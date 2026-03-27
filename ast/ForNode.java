package ast;

public class ForNode extends ControlSentenceNode{
    public ForNode(ExpressionNode boolean_expression, BlockNode block) {
        super(boolean_expression, block);
    }

}
