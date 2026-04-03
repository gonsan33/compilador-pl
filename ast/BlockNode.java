package ast;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends SentenceNode {
    protected ArrayList<SentenceNode> instructions; 
    
    public BlockNode(ArrayList<SentenceNode> instructions) {
        this.instructions = instructions; 
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Block(");
        for (int i = 0; i < instructions.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(instructions.get(i));
        }
        sb.append(")");
        return sb.toString();
    }
}
