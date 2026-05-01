package ast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import bind.SymbolTable;
import errors.GestionErroresTiny;

public class FunctionNode extends SentenceNode {
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
        if (returnType != null)
            s += ", returnType = " + returnType;
        if (body != null)
            s += ", body = " + body;
        return s + ")";
    }

    @Override
    public void bind() {
        SymbolTable st = SymbolTable.getSymbolTable();

        /*
         * Como permitimos declarar una función sin código, hay que permitir declarar
         * una función, sin su código,
         * y luego volver a declararla, esta vez con código.
         * Por lo tanto, hay que recorrer la tabla de símbolos para que, si coinciden
         * los identificadores, le pongamos a
         * la declaración su código.
         */
        FunctionNode oldDeclaration = (FunctionNode) st.lookup(identifier);
        if (oldDeclaration == null) {
            st.insert(identifier, this);
        } else if (oldDeclaration.body == null && this.body != null) {
            oldDeclaration.modify(this.params, this.returnType, this.body);
        } else {
            new GestionErroresTiny()
                    .errorBinding("No se permite declarar dos veces una funcion o redefinirla: " + identifier);
        }

        if (returnType != null)
            returnType.bind();

        st.openScope();
        for (ParameterNode param : params)
            param.bind();

        if (body != null)
            body.bind();
        st.closeScope();
    }

    public void modify(ArrayList<ParameterNode> params, TypeNode returnType, BlockNode body) {
        this.params = params;
        this.returnType = returnType;
        this.body = body;
    }

    public ArrayList<ParameterNode> getParams() {
        return params;
    }

    @Override
    public void typeCheck() {
        this.body.setFunction(this);
        for (ParameterNode param : params)
            param.typeCheck();
        if (returnType != null)
            returnType.typeCheck();
        if (body != null)
            body.typeCheck();
        
    }

    @Override
    public StringBuilder generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

}
