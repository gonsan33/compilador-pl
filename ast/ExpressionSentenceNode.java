package ast;
//sirve para poder tener llamadas a función como sentencia. 
public class ExpressionSentenceNode extends SentenceNode {
    protected ExpressionNode expression;

    public ExpressionSentenceNode(ExpressionNode expression) {
        this.expression = expression;
    }

    public String toString() {
        return "ExpressionSentence(expression = " + expression + ")";
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
        return expression.generateCode();
    }
    
}
