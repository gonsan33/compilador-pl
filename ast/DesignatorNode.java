package ast;

public abstract class DesignatorNode extends ExpressionNode {
    protected int delta;

    public abstract StringBuilder generateCodeL();

    public StringBuilder generateCode() {
        StringBuilder str = this.generateCodeL();
        str.append("i32.load\n");
        return str;
    }

    public int getDelta() {
        return delta;
    }

}
