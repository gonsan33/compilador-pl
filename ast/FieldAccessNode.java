package ast;

import errors.GestionErroresTiny;

// es el nodo que implica acceder al campo de un struct, eg. `myStruct.x`

public class FieldAccessNode extends DesignatorNode{
    protected DesignatorNode designator;
    protected String field;
    ParameterNode param; 

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
        TypeNode designatorType = designator.getType();
        if (designatorType instanceof CustomTypeNode) {
            designatorType = ((CustomTypeNode) designatorType).getDeclaredType();
        }
        if (!(designatorType instanceof StructTypeNode)) {
            new GestionErroresTiny().errorTipos("El designador no es un struct");
            return;
        }
        StructTypeNode type = (StructTypeNode) designatorType;  
        this.param = type.getField(this.field); 
        if(param == null) {
            new GestionErroresTiny().errorTipos("El struct no tiene un campo llamado " + this.field);
            this.type = new IntTypeNode();
            return;
        }
        this.type = param.getType(); 
    }


    @Override
    public TypeNode getType() {
        return this.type; 
    }

    @Override
    public StringBuilder generateCodeL() {
        StringBuilder code = new StringBuilder();
        code.append(designator.generateCodeL());
        code.append("i32.const " + this.param.getDelta() + "\n");
        code.append("i32.add\n");
        return code;
    }
}
