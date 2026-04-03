package ast;

public class IdentifierNode extends ExpressionNode{
    protected String identifier;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {return identifier;}
}
