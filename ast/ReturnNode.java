package ast;

public class ReturnNode extends SentenceNode {
    protected ExpressionNode expression;
    protected FunctionNode function;

    public ReturnNode(ExpressionNode expression) {
        this.expression = expression;
    }

    public void setFunction(FunctionNode function) {
        this.function = function;
    }

    public String toString() {
        return "Return(expr = " + expression + ")";
    }

    @Override
    public void bind() {
        expression.bind();
    }

    @Override
    public void typeCheck() {
        this.expression.typeCheck();
        if (this.function.returnType == null) {
            new errors.GestionErroresTiny().errorTipos("La función no tiene tipo de retorno");
        } else if (!this.expression.getType().equals(this.function.returnType)) {
            new errors.GestionErroresTiny()
                    .errorTipos("El tipo de la expresión de retorno no coincide con el tipo de retorno de la función");
        }
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
