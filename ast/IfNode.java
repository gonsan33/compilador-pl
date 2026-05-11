package ast;

import java.util.ArrayList;
import java.util.List;

public class IfNode extends ControlSentenceNode {
    protected BlockNode elseBlock;

    public IfNode(ExpressionNode boolean_expression, BlockNode block, BlockNode elseBlock) {
        super(boolean_expression, block);
        this.elseBlock = elseBlock;
    }

    public String toString() {
        String s = "If(cond = " + boolean_expression + ", then = " + block;
        if (elseBlock != null)
            s += ", else = " + elseBlock;
        return s + ")";
    }

    public List<ASTNode> hijos() {
        List<ASTNode> hijos = new ArrayList<>();
        if (block != null)
            hijos.add(block);
        if (elseBlock != null)
            hijos.add(elseBlock);
        return hijos;
    }

    @Override
    public void bind() {
        boolean_expression.bind();
        block.bind();
        if (elseBlock != null)
            elseBlock.bind();
    }

    @Override
    public void typeCheck() {
        super.typeCheck();
        if (elseBlock != null)
            elseBlock.typeCheck();
    }

    @Override
    public void setFunction(FunctionNode function) {
        super.setFunction(function);
        elseBlock.setFunction(function);
        
    }

    @Override
    public int asignarDelta(int acum) {
        int thenEnd = block != null ? block.asignarDelta(acum) : acum;
        int elseEnd = acum;
        if (elseBlock != null) {
            elseEnd = elseBlock.asignarDelta(acum);
        }
        return Math.max(thenEnd, elseEnd);
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        str.append(this.boolean_expression.generateCode());
        str.append("if");
        str.append(this.block.generateCode());
        if (elseBlock != null) {
            str.append("else \n");
            str.append(elseBlock.generateCode());
        }
        str.append("end \n");
        return str;
    }
}
