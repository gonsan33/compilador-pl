package ast;

public class BoolTypeNode extends TypeNode {
    public String toString() {
        return "bool";
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
        return "bool";
    }

}
