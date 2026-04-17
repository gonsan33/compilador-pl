package ast;

import bind.SymbolTable;

public class VariableDeclarationNode extends SentenceNode{
    protected String identifier; 
    protected TypeNode type; 
    protected ExpressionNode expression = null;
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

    public String toString() {
        String s = "VariableDeclaration(id = " + identifier + ", type = " + type;
        if (expression != null) s += ", expr = " + expression;
        if (newNode != null) s += ", new = " + newNode;
        return s + ")";
    }

    @Override
    public void bind() {
        type.bind();
        if (expression != null) expression.bind();
        if (newNode != null) newNode.bind();
        SymbolTable.getSymbolTable().insert(identifier, this);
    }

    public TypeNode getType(){
        return this.type; 
    }
}
