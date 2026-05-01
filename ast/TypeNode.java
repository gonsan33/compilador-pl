package ast;

public abstract class TypeNode implements ASTNode {

    @Override
    public StringBuilder generateCode() {
        return new StringBuilder();
    }

    public abstract boolean equals(TypeNode other);

    public abstract int size();

    public abstract String typeName();

}
