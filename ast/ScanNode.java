package ast;

public class ScanNode extends SentenceNode{
    protected ExpressionNode designator;

    public ScanNode(ExpressionNode designator) {
        this.designator = designator;
    }

    public String toString() {
        return "Scan(designator = " + designator + ")";
    }
}
