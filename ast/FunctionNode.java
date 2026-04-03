package ast;

import java.util.ArrayList;
import bind.SymbolTable;

public class FunctionNode extends SentenceNode{
    protected String identifier;
    protected ArrayList<ParameterNode> params;
    protected TypeNode returnType;
    protected BlockNode body;

    public FunctionNode(String identifier, ArrayList<ParameterNode> params, TypeNode returnType, BlockNode body) {
        this.identifier = identifier;
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    public String toString() {
        String s = "Function(id = " + identifier + ", params = " + params;
        if (returnType != null) s += ", returnType = " + returnType;
        if (body != null) s += ", body = " + body;
        return s + ")";
    }

    @Override
    public void bind() {
        SymbolTable st = SymbolTable.getSymbolTable();
        st.insert(identifier, this);

        if (returnType != null)
            returnType.bind();
        
        st.openScope();
        for (ParameterNode param : params)
            param.bind();
        
        if (body != null) 
            body.bind();
        st.closeScope();
    }
}
