package ast;

public interface ASTNode {
    public String toString(); 
    public void bind(); 
    public void typeCheck();
    public void generateCode(); 
}

