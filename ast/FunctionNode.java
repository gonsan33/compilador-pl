package ast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        if (returnType != null) {// Si no es void
            str.append(" (result i32)");
        }
        str.append("\n");
        str.append("  (local $localsStart i32)\n");
        str.append("  (local $temp i32)\n");
        int tamTotal = 0;
        for (ParameterNode param : params) {
            param.setDelta(tamTotal);
            tamTotal += param.esPorReferencia() ? 4 : param.getType().size();
        }
        if (body != null)
            tamTotal = body.asignarDelta(tamTotal); // Delta de las locales que declare
        str.append("  i32.const " + tamTotal + "\n");
        str.append("  call $reserveStack\n");
        str.append("  local.set $temp\n");
        str.append("  global.get $MP\n");
        str.append("  local.get $temp\n");
        str.append("  i32.store\n");
        str.append("  global.get $MP\n");
        str.append("  i32.const 4\n");
        str.append("  i32.add\n");
        str.append("  local.set $localsStart\n");
        str.append(body.generateCode());
        if (returnType != null) {// Si es tipo void, no tengo claro si esto es necesario, pero por si acaso, lo
                                 // dejo
            str.append("  i32.const 0\n");
        }
        str.append("  call $freeStack\n");
        str.append(")\n");
        return str;
    }

    public TypeNode getReturnType() {
        return returnType;
    }

    @Override
    public void typeCheck() {
        if (body != null)
            this.body.setFunction(this);
        for (ParameterNode param : params)
            param.typeCheck();
        if (returnType != null)
            returnType.typeCheck();
        if (body != null)
            body.typeCheck();

    }

    public List<ASTNode> hijos() {
        List<ASTNode> hijos = new ArrayList<>();
        for (ParameterNode param : params)
            hijos.add(param);
        if (body != null)
            hijos.add(body);
        return hijos;
    }

    public void maxSize(ASTNode a, Integer c, Integer max) {
        if (a instanceof VariableDeclarationNode) {
            c += ((VariableDeclarationNode) a).getType().size();
            if (c > max)
                max = c;
        } else if (a instanceof BlockNode) {
            Integer c1 = c;
            for (SentenceNode n : ((BlockNode) a).instructions) {
                maxSize(n, c, max);
            }
            c = c1;
        } else {
            for (ASTNode n : a.hijos()) {
                maxSize(n, c, max);
            }
        }
    }

}
