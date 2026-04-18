package ast;

import bind.SymbolTable;
import errors.GestionErroresTiny;

public class DeleteNode extends SentenceNode{
    protected DesignatorNode designator;
    protected VariableDeclarationNode declaration; 
    public DeleteNode(DesignatorNode designator) {
        this.designator = designator;
    }

    public String toString() {
        return "Delete(designator = " + designator + ")";
    }

    @Override
    public void bind() {
        designator.bind();
    }

    @Override
    public void typeCheck() {
        //hay que comprobar que el designador es un puntero
        designator.typeCheck();
        if (! (designator.getType()  instanceof PointerTypeNode) ) new GestionErroresTiny().errorTipos("Error de tipos: solo se puede hacer delete de un puntero");
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
