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
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        str.append("(func $" + identifier);
        if (returnType != null ) {//Si no es void
            str.append(" (result i32)");
        }
        str.append("\n");
        int tamParams = 0;
        for (ParameterNode p : params) {//Delta de params
            tamParams += p.getType().size(); 
        }
        int tamLocales = body.asignarDelta(4 + tamParams); //Delta de las locales que declare
        int tamTotal = 4 + tamParams + tamLocales;
        // Metemos la dirección a la que hay que volver
        str.append("  global.get $SP\n");
        str.append("  global.get $MP\n"); 
        str.append("  i32.store\n");      
        //Mi nuevo MP es el SP
        str.append("  global.get $SP\n");
        str.append("  global.set $MP\n");
        //Le sumo el espacio que necesito para mis variables locales y parámetros
        str.append("  global.get $SP\n");
        str.append("  i32.const " + tamTotal + "\n");
        str.append("  i32.add\n");
        str.append("  global.set $SP\n");
        str.append("  global.get $MP\n");
        str.append("  i32.swap\n");
        str.append("  i32.store\n"); 
        str.append(body.generateCode());
        str.append("  global.get $MP\n");
        str.append("  global.set $SP\n");
        str.append("  global.get $MP\n");
        str.append("  i32.load\n");
        str.append("  global.set $MP\n");
        if (returnType != null) {
            str.append("  i32.const 0\n"); 
        }
        str.append("  return\n");
        str.append(")\n");
        return str;
    }

    public TypeNode getReturnType() {
        return returnType;
    }

}
