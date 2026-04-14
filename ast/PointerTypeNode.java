package ast;

public class PointerTypeNode extends TypeNode{
    protected TypeNode baseType;

    public PointerTypeNode(TypeNode baseType) {
        this.baseType = baseType;
    }

    public String toString() {
        return "Pointer(baseType = " + baseType + ")";
    }

    @Override
    public void bind() {
        baseType.bind();
    }

    @Override
    public void typeCheck() {
        this.baseType.typeCheck();
    }

    @Override
    public boolean equals(TypeNode other) {
        return other.getClass().equals(this.getClass()) && ((PointerTypeNode) other).baseType.equals(this.baseType); 

    }

    @Override
    public int size() {
        return 4; 
    }
}
