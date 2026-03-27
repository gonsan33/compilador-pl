package ast;

public class VariableDeclarationNode extends SentenceNode{
    protected String identifier; 
    protected TypeNode type; 

    protected ExpressionNode expression = null ;
    protected NewNode newNode = null;
    public VariableDeclarationNode(String id, TypeNode type) {
        this.identifier = id; 
        this.type = type; 
    }
    public VariableDeclarationNode(String id, TypeNode type, ExpressionNode expr) {
        this.identifier = id; 
        this.type = type; 
        this.expression = expr; 
    }
    public VariableDeclarationNode(String id, TypeNode type, NewNode nn) {
        this.identifier = id; 
        this.type = type; 
        this.newNode = nn; 
    }
}
