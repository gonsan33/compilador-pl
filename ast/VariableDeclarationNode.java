package ast;

import bind.SymbolTable;

public class VariableDeclarationNode extends SentenceNode {
    protected String identifier;
    protected TypeNode type;
    protected ExpressionNode expression = null;
    protected NewNode newNode = null;

    public VariableDeclarationNode(String id, TypeNode type) {
        this.identifier = id;
        this.type = type;
    }

    public VariableDeclarationNode(String id, TypeNode type, ExpressionNode expr) {
        this.identifier = id;
        this.type = type;
        this.expression = expr;
    }

    public VariableDeclarationNode(String id, TypeNode type, NewNode nn) {
        this.identifier = id;
        this.type = type;
        this.newNode = nn;
    }

    public String toString() {
        String s = "VariableDeclaration(id = " + identifier + ", type = " + type;
        if (expression != null)
            s += ", expr = " + expression;
        if (newNode != null)
            s += ", new = " + newNode;
        return s + ")";
    }

    @Override
    public void bind() {
        type.bind();
        if (expression != null)
            expression.bind();
        if (newNode != null)
            newNode.bind();
        SymbolTable.getSymbolTable().insert(identifier, this);
    }

    public TypeNode getType() {
        return this.type;
    }

    @Override
    public void typeCheck() {
        type.typeCheck();
        if (expression != null)
            expression.typeCheck();
        if (expression != null && !expression.getType().equals(type)) {
            new errors.GestionErroresTiny()
                    .errorTipos("El tipo de la expresión no coincide con el declarado para la variable");
        }
        if (newNode != null)
            newNode.typeCheck();
        if (newNode != null && !type.equals(newNode.getType())) {
            new errors.GestionErroresTiny().errorTipos("El tipo del new no coincide con el declarado para la variable");
        }
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
