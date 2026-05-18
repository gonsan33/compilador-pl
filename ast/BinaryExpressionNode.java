package ast;

import errors.GestionErroresTiny;

public class BinaryExpressionNode extends ExpressionNode {
    protected ExpressionNode left;
    protected ExpressionNode right;
    protected BinaryOperation op;

    public BinaryExpressionNode(BinaryOperation op, ExpressionNode left, ExpressionNode right) {
        this.op = op;
        this.left = left;
        this.right= right;
    }

    public String toString() {
        return op.toString() + "(left = " +  left.toString() + ", right = " + right.toString() + ")";
    }

    @Override
    public void bind() {
        left.bind();
        right.bind();
    }

    @Override
    public void typeCheck() {
        left.typeCheck();
        right.typeCheck();
        switch(this.op) {
            case SUM:
            case DIFFERENCE:
            case MULTIPLICATION:
            case DIVISION:
            case MODULE:
            case LEQ:
            case LT:
            case GEQ:
            case GT:
                if(! this.left.getType().equals( new IntTypeNode()) || ! this.right.getType().equals(new IntTypeNode()) ) new GestionErroresTiny().errorTipos("Error de tipos: ambos operandos deben tener tipo entero");
                break;
            case EQ :
                boolean valid = this.left.getType().equals(new IntTypeNode())  && this.right.getType().equals(new IntTypeNode()) || this.left.getType().equals(new BoolTypeNode())  && this.right.getType().equals(new BoolTypeNode()) || this.left.getType().equals(this.right.getType()) && (this.left.getType()  instanceof PointerTypeNode);
                if(!valid) new GestionErroresTiny().errorTipos("Error de tipos: ambos operandos deben ser enteros, booleanos o punteros del mismo tipo");
                break;
            case AND:
            case OR:
                if(! this.left.getType().equals( new BoolTypeNode()) || ! this.right.getType().equals(new BoolTypeNode()) ) new GestionErroresTiny().errorTipos("Error de tipos: ambos operandos deben tener tipo bool");
                break;
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        str.append(this.left.generateCode());
        str.append(this.right.generateCode());
        switch (op) {
            case SUM:
                str.append("i32.add");
                break;
            case DIFFERENCE:
                str.append("i32.sub");
                break;
            case MULTIPLICATION:
                str.append("i32.mul");
                break;
            case DIVISION:
                str.append("i32.div_s");
                break;
            case MODULE:
                str.append("i32.rem_s");
                break;
            case LEQ:
                str.append("i32.le_s");
                break;
            case LT:
                str.append("i32.lt_s");
                break;
            case GEQ:
                str.append("i32.ge_s");
                break;
            case GT:
                str.append("i32.gt_s");

                break;
            case EQ :
                str.append("i32.eq");
                break;
            case AND:
                str.append("i32.and");
                break;
            case OR:
                str.append("i32.or");
                break;
        }
        str.append(System.lineSeparator());
        return str;
    }

    @Override
    public TypeNode getType() {
        TypeNode result = null;
        switch(this.op) {
            case SUM:
            case DIFFERENCE:
            case MULTIPLICATION:
            case DIVISION:
            case MODULE:
                result = new IntTypeNode();
                break;
            case LEQ:
            case LT:
            case GEQ:
            case GT:
            case EQ:
            case AND:
            case OR:
                result = new BoolTypeNode();
                break;
        }
        this.type = result;
        return result;
    }
}
