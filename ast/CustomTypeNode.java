package ast;

import bind.SymbolTable;
import errors.GestionErroresTiny;

public class CustomTypeNode extends TypeNode {
    protected String identifier;
    protected TypeDeclarationNode declaration;

    public CustomTypeNode(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {
        return identifier;
    }

    @Override
    public void bind() {
        declaration = (TypeDeclarationNode) SymbolTable.getSymbolTable().lookup(identifier);
        if (declaration == null) {
            new GestionErroresTiny().errorBinding("Tipo no declarado: " + identifier);
        }
    }

    @Override
    public void typeCheck() {

    }

    public TypeNode getDeclaredType() {
        return declaration.getType();
    }

    @Override
    public boolean equals(TypeNode other) {
        return this.identifier.equals(other.typeName());
    }

    @Override
    public int size() {
        return declaration.size();
    }

    @Override
    public String typeName() {
        return this.identifier;
    }

    @Override
    public int asignarDelta(int acum) {
        return declaration.getType().asignarDelta(acum);
    }
}
