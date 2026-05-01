package ast;

public interface ASTNode {
    public String toString(); 
    public void bind(); 
    public void typeCheck();
    public StringBuilder generateCode();  
    public default int asignarDelta(int acum){
        return acum;
    }
}

