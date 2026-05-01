package ast;

import errors.GestionErroresTiny;

public class PointerAccessNode extends DesignatorNode {
    protected DesignatorNode designator;

    public PointerAccessNode(DesignatorNode designator) {
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
    public TypeNode getType() {
        return ((PointerTypeNode) this.designator.getType()).getBaseType();
    }


    @Override
    public StringBuilder generateCodeL() {
        StringBuilder str = new StringBuilder();
        str.append(designator.generateCodeL());
        str.append("i32.load\n");
        return str;
    }

}
