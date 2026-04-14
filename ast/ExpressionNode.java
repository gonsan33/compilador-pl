package ast;

public abstract class ExpressionNode implements ASTNode {

    protected TypeNode type; 

    public abstract TypeNode getType() ; 

}
