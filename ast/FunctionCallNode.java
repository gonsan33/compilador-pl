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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }

    @Override
    public TypeNode getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }
}
