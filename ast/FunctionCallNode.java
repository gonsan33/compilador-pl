package ast;

import java.util.ArrayList;
import bind.SymbolTable;
import errors.GestionErroresTiny;

public class FunctionCallNode extends ExpressionNode{
    protected String identifier;
    protected ArrayList<ExpressionNode> args;
    protected ASTNode declaration;

    public FunctionCallNode(String identifier, ArrayList<ExpressionNode> args) {
        this.identifier = identifier;
        this.args = args;
    }

    public String toString() {
        return "FunctionCall(id = " + identifier + ", args = " + args + ")";
    }

    @Override
    public void bind() {
        declaration = SymbolTable.getSymbolTable().lookup(identifier);
        if (declaration == null) {
            new GestionErroresTiny().errorBinding("Funcion no declarada: " + identifier);
        }
        for (ExpressionNode arg : args) {
            arg.bind();
        }
    }
}
