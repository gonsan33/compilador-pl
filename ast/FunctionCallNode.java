package ast;

import java.util.ArrayList;
import bind.SymbolTable;
import errors.GestionErroresTiny;

public class FunctionCallNode extends DesignatorNode {
    protected String identifier;
    protected ArrayList<ExpressionNode> args;
    protected FunctionNode declaration;

    public FunctionCallNode(String identifier, ArrayList<ExpressionNode> args) {
        this.identifier = identifier;
        this.args = args;
    }

    public String toString() {
        return "FunctionCall(id = " + identifier + ", args = " + args + ")";
    }

    @Override
    public void bind() {
        declaration = (FunctionNode) SymbolTable.getSymbolTable().lookup(identifier);
        if (declaration == null) {
            new GestionErroresTiny().errorBinding("Funcion no declarada: " + identifier);
        }
        for (ExpressionNode arg : args) {
            arg.bind();
        }
    }

    @Override
    public void typeCheck() {
        for (ExpressionNode arg : args) {
            arg.typeCheck();
        }
        if (declaration.getParams().size() != args.size()) {
            // Lanzo error
            new GestionErroresTiny()
                    .errorTipos("La lista de argumentos de la declaración no tiene el mismo tamaño que args");
        }
        for (int i = 0; i < args.size(); ++i) {
            ParameterNode param = declaration.getParams().get(i);
            ExpressionNode arg = args.get(i);
            if (!arg.getType().equals(param.getType())) {
                new GestionErroresTiny().errorTipos("El parámetro" + i + " no coincide con la declaracion");
            }
            if (param.esPorReferencia() && !(arg instanceof DesignatorNode)) {
                new GestionErroresTiny().errorTipos("El parámetro " + i + " debe pasarse por referencia");
            }
            if (!param.esPorReferencia() && param.getType().size() > 4 && !(arg instanceof DesignatorNode)) {
                new GestionErroresTiny().errorTipos("El parámetro compuesto " + i + " debe ser un designador");
            }
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder(); 
        for (int i = 0; i < args.size(); ++i) {
            generarCodigoArgumento(args.get(i), i, str);
        }
        str.append("call $" + identifier + "\n"); // REVISAR ESTO !!!!!!!!!!
        return str;
    }

    @Override
    public TypeNode getType() {
        return declaration.getReturnType();
    }

    private void generarCodigoArgumento(ExpressionNode arg, int index, StringBuilder str) {
        ParameterNode param = declaration.getParams().get(index); 
        // Parámetro por valor escalar (1 slot)
        if(!param.esPorReferencia() && param.getType().size() == 4 ) {
            str.append("global.get $SP\n"); 
            str.append("i32.const " + param.getDelta() + "\n");
            str.append("i32.add\n");
            str.append(arg.generateCode());
            str.append("i32.store offset=4\n");
        }
        //parámetro por referencia 
        else if(param.esPorReferencia()) {
            str.append("global.get $SP\n"); 
            str.append("i32.const " + param.getDelta() + "\n");
            str.append("i32.add\n");
            str.append(((DesignatorNode) arg).generateCodeL());
            str.append("i32.store offset=4\n");
        }

        else { //parámetro por valor complicado 
            str.append(((DesignatorNode) arg).generateCodeL());
            str.append("global.get $SP\n"); 
            str.append("i32.const " + (param.getDelta()+4) + "\n");
            str.append("i32.add\n");
            str.append("i32.const " + (param.getType().size() / 4) + "\n"); 
            str.append("call $copyn\n"); 
        }
    }

    @Override
    public StringBuilder generateCodeL() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCodeL'");
    }
        
}
