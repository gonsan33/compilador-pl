package ast;

public class ArrayTypeNode extends TypeNode{
    protected String size;
    protected TypeNode elementType;

    public ArrayTypeNode(String size, TypeNode elementType) {
        this.size = size;
        this.elementType = elementType;
    }

    public String toString() {
        return "Array(size = " + size + ", elementType = " + elementType + ")";
    }

    @Override
    public void bind() {
        elementType.bind();
    }

    @Override
    public void typeCheck() {
        Integer.parseInt(size); 
        this.elementType.typeCheck();
    }

    @Override
    public boolean equals(TypeNode other) {
        return (other instanceof ArrayTypeNode) && this.elementType.equals(((ArrayTypeNode) other).elementType); 
    }

    @Override
    public int size() {
        return this.elementType.size() * Integer.parseInt(this.size); 
    }

    public TypeNode getElementType() {
        return this.elementType;
    }

    @Override
    public String typeName() {
        return "Array(size = " + size + ", elementType = " + elementType + ")"; 
    }
}
