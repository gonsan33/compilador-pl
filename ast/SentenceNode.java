package ast;

public abstract class SentenceNode implements ASTNode{

    @Override
    public abstract void bind();

    @Override
    public abstract void typeCheck() ;

    
    public abstract StringBuilder generateCode() ;

}
