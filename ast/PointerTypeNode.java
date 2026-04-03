package ast;

public class PointerTypeNode extends TypeNode{
    protected TypeNode baseType;

    public PointerTypeNode(TypeNode baseType) {
        this.baseType = baseType;
    }

    public String toString() {
        return "Pointer(baseType = " + baseType + ")";
    }
}
