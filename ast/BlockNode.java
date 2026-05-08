package ast;

import java.util.ArrayList;
import bind.SymbolTable;

public class BlockNode extends SentenceNode {
    protected ArrayList<SentenceNode> instructions;

    public BlockNode(ArrayList<SentenceNode> instructions) {
        this.instructions = instructions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Block(");
        for (int i = 0; i < instructions.size(); i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(instructions.get(i));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void bind() {
        SymbolTable st = SymbolTable.getSymbolTable();
        st.openScope();
        for (SentenceNode instruction : instructions) {
            instruction.bind();
        }
        st.closeScope();
    }

    @Override
    public void typeCheck() {
        for (SentenceNode node : this.instructions) {
            node.typeCheck();
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        for (SentenceNode instruction : this.instructions) {
            str.append(instruction.generateCode());
        }
        return str;
    }

    public void setFunction(FunctionNode function) {
        for (SentenceNode node : this.instructions) {
            if (node instanceof ReturnNode) {
                ((ReturnNode) node).setFunction(function);
            } else if (node instanceof BlockNode) {
                ((BlockNode) node).setFunction(function);
            } else if (node instanceof ControlSentenceNode) {
                ((ControlSentenceNode) node).setFunction(function);
            }
        }
    }

    public int asignarDelta(int acum) {
        acum=0;
        for (SentenceNode node : this.instructions) {
            acum = node.asignarDelta(acum);
        }
        return acum;
    }
}
