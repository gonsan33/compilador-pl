package ast;

import errors.GestionErroresTiny;

public class ArrayAccessNode extends DesignatorNode {
    protected ExpressionNode designator;
    protected ExpressionNode index;

    public ArrayAccessNode(ExpressionNode designator, ExpressionNode index) {
        this.designator = designator;
        this.index = index;
    }

    public String toString() {
        return "ArrayAccess(designator = " + designator + ", index = " + index + ")";
    }

    @Override
    public void bind() {
        designator.bind();
        index.bind();
    }

    @Override
    public void typeCheck() {
        index.typeCheck();
        designator.typeCheck();
        if (!index.getType().equals(new IntTypeNode())) {
            new GestionErroresTiny().errorTipos("La expresión debe ser entera");
        }
        if (!(designator instanceof DesignatorNode)) {
            new GestionErroresTiny().errorTipos("El designador debe ser designador");
        }
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

    @Override
    public TypeNode getType() {
        return designator.getType(); 
    }
}
