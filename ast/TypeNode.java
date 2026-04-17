package ast;

public abstract class TypeNode implements ASTNode {

    @Override
    public void generateCode() {
    }

    public abstract boolean equals(TypeNode other);

    public abstract int size();

    public abstract String typeName();

}
