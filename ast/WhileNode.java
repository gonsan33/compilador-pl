package ast;

public class WhileNode extends ControlSentenceNode{

    public WhileNode(ExpressionNode boolean_expression, BlockNode block) {
        super(boolean_expression, block);
    }

}

