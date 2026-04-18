package ast;

import errors.GestionErroresTiny;

// es el nodo que implica acceder al campo de un struct, eg. `myStruct.x`

public class FieldAccessNode extends DesignatorNode{
    protected DesignatorNode designator;
    protected String field;

    public FieldAccessNode(DesignatorNode designator, String field) {
        this.designator = designator;
        this.field = field;
    }

    public String toString() {
        return "FieldAccess(designator = " + designator + ", field = " + field + ")";
    }

    @Override
    public void bind() {
        designator.bind();
    }

    @Override
    public void typeCheck() {
        designator.typeCheck();
        StructTypeNode type = (StructTypeNode) designator.getType();  
        ParameterNode param = type.getField(this.field); 
        if(param == null) new GestionErroresTiny().errorTipos("El struct no tiene un campo llamado " + this.field);
        this.type = param.getType(); 
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

    @Override
    public TypeNode getType() {
        return this.type; 
    }
}
