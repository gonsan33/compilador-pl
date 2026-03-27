package ast;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends SentenceNode {
    protected ArrayList<SentenceNode> instructions; 
    
    public BlockNode(ArrayList<SentenceNode> instructions ) {
        this.instructions = instructions; 
    }
}
