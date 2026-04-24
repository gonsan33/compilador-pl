package ast;

public class UnaryExpressionNode extends ExpressionNode {
    protected ExpressionNode exp;
    protected UnaryOperation op;

    public UnaryExpressionNode(UnaryOperation op, ExpressionNode exp) {
        this.op = op;
        this.exp = exp;
    }

    public String toString() {
        return op.toString() + "(exp = " + exp.toString() + ")";
    }

    @Override
    public void bind() {
        exp.bind();
    }

    @Override
    public void typeCheck() {
        exp.typeCheck();
        if (op == UnaryOperation.AMPERSAND) {
            if (!(exp instanceof DesignatorNode)) {
                new errors.GestionErroresTiny().errorTipos("El operador & solo se puede aplicar a designadores");
            }
        } else if (op == UnaryOperation.MAS || op == UnaryOperation.MENOS) {
            if (!exp.getType().equals(new IntTypeNode())) {
                new errors.GestionErroresTiny().errorTipos("Los operadores + y - solo se pueden aplicar a enteros");
            }
        } else if (op == UnaryOperation.NOT) {
            if (!exp.getType().equals(new BoolTypeNode())) {
                new errors.GestionErroresTiny().errorTipos("El operador ! solo se puede aplicar a booleanos");
            }
        }
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

    @Override
    public TypeNode getType() {
        if (op == UnaryOperation.AMPERSAND) {
            return new PointerTypeNode(exp.getType());
        } else if (op == UnaryOperation.MAS || op == UnaryOperation.MENOS) {
            return new IntTypeNode();
        } else if (op == UnaryOperation.NOT) {
            return new BoolTypeNode();
        }
        return null;
    }

}