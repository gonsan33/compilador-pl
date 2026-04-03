package ast;

public class CustomTypeNode extends TypeNode{
    protected String identifier;

    public CustomTypeNode(String identifier) {
        this.identifier = identifier;
    }

    public String toString() {return identifier;}
}
