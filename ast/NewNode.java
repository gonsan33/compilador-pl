package ast;

public class NewNode implements ASTNode {
    protected TypeNode type;

    public NewNode(TypeNode type) {
        this.type = type;
    }

    public String toString() {
        return "New(type = " + type + ")";
    }

    @Override
    public void bind() {
        type.bind();
    }

    @Override
    public void typeCheck() {
        type.typeCheck();
    }

    @Override
    public StringBuilder generateCode() {
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

    public TypeNode getBaseLineType() {
        return this.type;
    }

    public TypeNode getType() {
        return new PointerTypeNode(this.type);
    }
}
