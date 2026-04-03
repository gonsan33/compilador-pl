package ast;

public class ParameterNode implements ASTNode{
    protected String identifier;
    protected TypeNode type;

    public ParameterNode(String identifier, TypeNode type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String toString() {
        return "Param(id = " + identifier + ", type = " + type + ")";
    }

    @Override
    public void bind() {
    }

    @Override
    public void typeCheck() {
    }

    @Override
    public void generateCode() {
    }
}
