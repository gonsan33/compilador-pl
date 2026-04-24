package ast;

import errors.GestionErroresTiny;

public class PointerAccessNode extends DesignatorNode {
    protected ExpressionNode designator;

    public PointerAccessNode(ExpressionNode designator) {
        this.designator = designator;
    }

    public String toString() {
        return "PointerAccess(designator = " + designator + ")";
    }

    @Override
    public void bind() {
        designator.bind();
    }

    @Override
    public void typeCheck() {
        if (!(designator.getType() instanceof PointerTypeNode)) {
            new GestionErroresTiny().errorTipos("El designador no es un puntero");
        }
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

    @Override
    public TypeNode getType() {
        return ((PointerTypeNode) this.designator.getType()).getBaseType();
    }
}
