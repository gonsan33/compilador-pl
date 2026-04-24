package ast;

import bind.SymbolTable;

public class TypeDeclarationNode extends SentenceNode {
    protected String identifier;
    protected TypeNode type;

    public TypeDeclarationNode(String identifier, TypeNode type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String toString() {
        return "TypeDeclaration(id = " + identifier + ", type = " + type + ")";
    }

    @Override
    public void bind() {
        type.bind();
        SymbolTable.getSymbolTable().insert(identifier, this);
    }

    public int size() {
        return type.size();
    }

    @Override
    public void typeCheck() {
        type.typeCheck();
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
