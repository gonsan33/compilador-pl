package ast;

public class DeleteNode extends SentenceNode{
    protected ExpressionNode designator;

    public DeleteNode(ExpressionNode designator) {
        this.designator = designator;
    }

    public String toString() {
        return "Delete(designator = " + designator + ")";
    }
}
