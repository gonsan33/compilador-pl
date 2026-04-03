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
        throw new UnsupportedOperationException("Unimplemented method 'typeCheck'");
    }

    @Override
    public void generateCode() {
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
