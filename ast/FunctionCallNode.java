package ast;

import java.util.ArrayList;

public class FunctionCallNode extends ExpressionNode{
    protected String identifier;
    protected ArrayList<ExpressionNode> args;

    public FunctionCallNode(String identifier, ArrayList<ExpressionNode> args) {
        this.identifier = identifier;
        this.args = args;
    }

    public String toString() {
        return "FunctionCall(id = " + identifier + ", args = " + args + ")";
    }
}
