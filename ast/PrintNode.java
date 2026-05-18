package ast;

import errors.GestionErroresTiny;

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
        if (!(expression.getType().equals(new IntTypeNode())  || expression.getType().equals(new BoolTypeNode()))) {
            GestionErroresTiny.getInstance().errorTipos("Error: print only accepts int and bool expressions");
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();

        str.append(expression.generateCode());

        str.append("  call $print\n");

        return str;
    }
}
