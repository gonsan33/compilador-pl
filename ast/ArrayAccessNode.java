package ast;

import errors.GestionErroresTiny;

public class ArrayAccessNode extends DesignatorNode {
    protected DesignatorNode designator;
    protected ExpressionNode index;

    public ArrayAccessNode(DesignatorNode designator, ExpressionNode index) {
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
            new GestionErroresTiny().errorTipos("El designador debe ser un designador");
        }
        this.type = designator.getType();
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();

        str.append(this.designator.generateCode());
        str.append(this.index.generateCode());

        str.append("i32.const ").append(this.getType().size()).append(System.lineSeparator());
        str.append("i32.mul").append(System.lineSeparator());
        str.append("i32.add").append(System.lineSeparator());
        
        // Accedemos al elemento solo si estamos procesando la última dimensión del array
        if (!(this.getType() instanceof ArrayTypeNode)) {
            str.append("i32.load").append(System.lineSeparator());
        }
        
        return str;
    }

    @Override
    public TypeNode getType() {
        return designator.getType(); 
    }

    @Override
    public int delta() {
        return 0;
    }
}
