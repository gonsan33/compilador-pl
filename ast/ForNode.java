package ast;

import java.util.ArrayList;
import java.util.List;

import bind.SymbolTable;

public class ForNode extends ControlSentenceNode {
    protected SentenceNode init;
    protected SentenceNode step;

    public ForNode(SentenceNode init, ExpressionNode boolean_expression, SentenceNode step, BlockNode block) {
        super(boolean_expression, block);
        this.init = init;
        this.step = step;
    }

    public String toString() {
        return "For(init = " + init + ", cond = " + boolean_expression + ", step = " + step + ", block = " + block
                + ")";
    }

    public List<ASTNode> hijos() {
        List<ASTNode> hijos = new ArrayList<>();
        if (init != null)
            hijos.add(init);
        if (boolean_expression != null)
            hijos.add(boolean_expression);
        if (step != null)
            hijos.add(step);
        if (block != null)
            hijos.add(block);
        return hijos;
    }

    @Override
    public void bind() {
        SymbolTable st = SymbolTable.getSymbolTable();
        st.openScope();
        if (init != null)
            init.bind();
        if (boolean_expression != null)
            boolean_expression.bind();
        if (step != null)
            step.bind();
        block.bind();
        st.closeScope();
    }

    @Override
    public void typeCheck() {
        if (init != null)
            init.typeCheck();
        if (boolean_expression != null) {
            boolean_expression.typeCheck();
            if (!boolean_expression.getType().equals(new BoolTypeNode())) {
                new errors.GestionErroresTiny().errorTipos("La expresión debe ser booleana");
            }
        }
        block.typeCheck();
        if (step != null)
            step.typeCheck();
    }

    @Override
    public int asignarDelta(int acum) {
        if (init != null)
            acum = init.asignarDelta(acum);
        if (block == null)
            return acum;
        return block.asignarDelta(acum);
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        if(init != null) str.append(init.generateCode());
        str.append("block\n");
        str.append("loop\n");
        str.append(boolean_expression.generateCode());
        str.append("i32.eqz\n");
        str.append("br_if 1\n");
        str.append(this.block.generateCode());
        if(step != null) str.append(step.generateCode());
        str.append("br 0 \n");
        str.append("end \n");
        str.append("end \n");
        return str;
    }
}
