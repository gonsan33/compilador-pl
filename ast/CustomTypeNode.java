package ast;

import bind.SymbolTable;
import errors.GestionErroresTiny;

public class CustomTypeNode extends TypeNode{
    protected String identifier;
    protected ASTNode declaration;

    public CustomTypeNode(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {return identifier;}

    @Override
    public void bind() {
        declaration = SymbolTable.getSymbolTable().lookup(identifier);
        if (declaration == null) {
            new GestionErroresTiny().errorBinding("Tipo no declarado: " + identifier);
        }
    }
}
