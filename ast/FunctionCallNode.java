package ast;

import java.util.ArrayList;
import bind.SymbolTable;
import errors.GestionErroresTiny;

public class FunctionCallNode extends ExpressionNode {
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
            if (!args.get(i).getType().equals(declaration.getParams().get(i).getType())
                    || !(args.get(i) instanceof DesignatorNode || !declaration.getParams().get(i).esPorReferencia())) {
                new GestionErroresTiny().errorTipos("El parámetro" + i + " no coincide con la declaracion");
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
        //Parámetro por valor básico 
        if(!param.esPorReferencia() && (param.getType().equals(new IntTypeNode()) || param.getType().equals(new BoolTypeNode()))  ) {
            str.append("global.get $SP\n"); 
            str.append("i32.const " + param.getDelta() + "\n");
            str.append("i32.add\n");
            str.append(arg.generateCode());
            str.append("i32.store offset = 4\n");
        }
        //parámetro por referencia 
        else if(param.esPorReferencia()) {
            str.append("global.get $SP\n"); 
            str.append("i32.const " + param.getDelta() + "\n");
            str.append("i32.add\n");
            str.append(((DesignatorNode) arg).generateCodeL());
            str.append("i32.store offset = 4\n");
        }

        else { //parámetro por valor complicado 
            str.append(((DesignatorNode) arg).generateCodeL());
            str.append("global.get $SP\n"); 
            str.append("i32.const " + (param.getDelta()+4) + "\n");
            str.append("i32.add\n");
            str.append("i32.const " + param.getType()); 
            str.append("copyn"); 
        }
        
    }
}
