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
        declaration = (ASTNode) SymbolTable.getSymbolTable().lookup(identifier);
        if (declaration == null) {
            new GestionErroresTiny().errorBinding("Identificador no declarado: " + identifier);
        }
    }

    @Override
    public void typeCheck() {
        if (declaration == null) {
            new errors.GestionErroresTiny().errorTipos("Identificador sin declaracion: " + identifier);
        }
    }

    @Override
    public TypeNode getType() {
        if (declaration instanceof ParameterNode) return ((ParameterNode) declaration).getType();
        return ((VariableDeclarationNode) declaration).getType();
    }

    public int delta() {
        this.delta = (declaration instanceof ParameterNode)
            ? ((ParameterNode) declaration).getDelta()
            : ((VariableDeclarationNode) declaration).getDelta();
        return this.delta;
    }

    @Override
    public StringBuilder generateCodeL() {
        StringBuilder code = new StringBuilder();
        code.append("i32.const "+ this.delta() + "\n");
        boolean isLocal = true;
        if (declaration instanceof VariableDeclarationNode) {
            isLocal = !((VariableDeclarationNode) declaration).isGlobal();
        }
        if (isLocal) {
            code.append("local.get $localsStart\n");
            code.append("i32.add\n");
        }
        if (declaration instanceof ParameterNode) {
            if (((ParameterNode) declaration).esPorReferencia()) { 
                code.append("i32.load\n");
            }
        }
        return code;
    }
}
