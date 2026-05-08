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
        StringBuilder str = new StringBuilder();
        int size = type.size();
        str.append("  i32.const ").append(size).append("\n");
        str.append("  call $reserveHeap\n");
        return str;
    }

    public TypeNode getBaseLineType() {
        return this.type;
    }

    public TypeNode getType() {
        return new PointerTypeNode(this.type);
    }
}
