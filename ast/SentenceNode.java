package ast;

public abstract class SentenceNode implements ASTNode{

    @Override
    public abstract void bind();

    @Override
    public abstract void typeCheck() ;

    @Override
    public abstract StringBuilder generateCode() ;

}
