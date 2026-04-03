package ast;

public class TypeDeclarationNode extends SentenceNode{
    protected String identifier;
    protected TypeNode type;

    public TypeDeclarationNode(String identifier, TypeNode type) {
        this.identifier = identifier;
        this.type = type;
    }

    public String toString() {
        return "TypeDeclaration(id = " + identifier + ", type = " + type + ")";
    }
}
