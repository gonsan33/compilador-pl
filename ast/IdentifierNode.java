package ast;

import bind.SymbolTable;
import errors.GestionErroresTiny;

public class IdentifierNode extends DesignatorNode{
    protected String identifier;
    protected VariableDeclarationNode declaration;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {return identifier;}

    @Override
    public void bind() {
        declaration = (VariableDeclarationNode) SymbolTable.getSymbolTable().lookup(identifier);
        if (declaration == null) {
            new GestionErroresTiny().errorBinding("Identificador no declarado: " + identifier);
        }
    }

    @Override
    public void typeCheck() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'typeCheck'");
    }


    @Override
    public TypeNode getType() {
        return this.declaration.getType(); 
    }

    
    public int delta() {
        this.delta = this.declaration.getDelta();
        return this.delta;
    }


    @Override
    public StringBuilder generateCodeL() {
        StringBuilder code = new StringBuilder();
        code.append("i32.const "+ this.delta() + "\n");
        code.append("local.get $localsStart\n");
        code.append("i32.add\n");   
        return code;
    }
}
