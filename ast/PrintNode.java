package ast;

public class PrintNode extends SentenceNode {
    protected ExpressionNode expression;

    public PrintNode(ExpressionNode expression) {
        this.expression = expression;
    }

    public String toString() {
        return "Print(expr = " + expression + ")";
    }

    @Override
    public void bind() {
        expression.bind();
    }

    @Override
    public void typeCheck() {
        expression.typeCheck();
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
