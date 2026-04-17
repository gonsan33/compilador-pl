package ast;

public class IntTypeNode extends TypeNode {
    public String toString() {
        return "int";
    }

    @Override
    public boolean equals(TypeNode other) {
        return this.getClass().equals(other.getClass());
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public void bind() {

    }

    @Override
    public void typeCheck() {

    }

    @Override
    public String typeName() {
        return "int";
    }
}
