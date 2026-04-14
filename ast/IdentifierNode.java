package ast;

import bind.SymbolTable;
import errors.GestionErroresTiny;

public class IdentifierNode extends DesignatorNode{
    protected String identifier;
    protected ASTNode declaration;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {return identifier;}

    @Override
    public void bind() {
        declaration = SymbolTable.getSymbolTable().lookup(identifier);
        if (declaration == null) {
            new GestionErroresTiny().errorBinding("Identificador no declarado: " + identifier);
        }
    }
}
