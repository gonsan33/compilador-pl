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
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();

        str.append(expression.generateCode());

        str.append("  call $print\n");

        return str;
    }
}
