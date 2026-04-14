package ast;

import bind.SymbolTable;

public class ParameterNode implements ASTNode{
    protected String identifier;
    protected TypeNode type;

    public ParameterNode(String identifier, TypeNode type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String toString() {
        return "Param(id = " + identifier + ", type = " + type + ")";
    }

    @Override
    public void bind() {
        type.bind();
        SymbolTable.getSymbolTable().insert(identifier, this);
    }

    @Override
    public void typeCheck() {}

    @Override
    public void generateCode() {}

    public TypeNode getType() {
        return this.type; 
    }

    public boolean equals(ParameterNode other) {
        return this.identifier.equals(other.identifier) && this.type.equals(other.type); 
    }
}
