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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'typeCheck'");
    }

    @Override
    public boolean equals(TypeNode other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'equals'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }
}
