package ast;

import bind.SymbolTable;
import errors.GestionErroresTiny;

public class CustomTypeNode extends TypeNode{
    protected String identifier;
    protected TypeDeclarationNode declaration;

    public CustomTypeNode(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {return identifier;}

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

    @Override
    public boolean equals(TypeNode other) {
        return this.getClass().equals(other.getClass()); 
    }

    @Override
    public int size() {
        return declaration.size(); 
    }
}
