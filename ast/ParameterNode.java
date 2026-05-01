package ast;

import bind.SymbolTable;

public class ParameterNode implements ASTNode {
    protected String identifier;
    protected TypeNode type;
    protected boolean ref;
    protected int delta; 

    public ParameterNode(String identifier, TypeNode type) {
        this.identifier = identifier;
        this.type = type;
        this.ref = false;
    }

    public ParameterNode(String identifier, TypeNode type, int a) {
        this.identifier = identifier;
        this.type = type;
        this.ref = true;
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
    public void typeCheck() {
        type.typeCheck();
    }

    public int getDelta() {
        return this.delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public TypeNode getType() {
        return this.type;
    }

    public boolean equals(ParameterNode other) {
        return this.identifier.equals(other.identifier) && this.type.equals(other.type) && this.ref == other.ref;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public boolean esPorReferencia() {
        return this.ref;
    }

    @Override
    public StringBuilder generateCode() {
        return new StringBuilder();
    }
}
