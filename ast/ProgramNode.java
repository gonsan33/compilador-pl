package ast;

import java.util.ArrayList;

public class ProgramNode extends SentenceNode{
    protected ArrayList<SentenceNode> instructions;

    public ProgramNode(ArrayList<SentenceNode> instructions) {
        this.instructions = instructions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Program(");
        for (int i = 0; i < instructions.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(instructions.get(i));
        }
        sb.append(")");
        return sb.toString();
    }
}
