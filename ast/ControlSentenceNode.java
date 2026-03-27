package ast;
//Class from which if, while and for sentences will inherit. 
//its arguments include a boolean expression and a mandatory BLOCK 
public class ControlSentenceNode implements ASTNode{
    protected ExpressionNode boolean_expression ; 
    protected BlockNode block; 

    public ControlSentenceNode(ExpressionNode boolean_expression, BlockNode  block ) {
        this.boolean_expression = boolean_expression; 
        this.block = block; 
    }

    @Override
    public void bind() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bind'");
    }

    @Override
    public void typeCheck() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'typeCheck'");
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

}
