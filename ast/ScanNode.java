package ast;

public class ScanNode extends SentenceNode {
    protected ExpressionNode designator;

    public ScanNode(ExpressionNode designator) {
        this.designator = designator;
    }

    public String toString() {
        return "Scan(designator = " + designator + ")";
    }

    @Override
    public void bind() {
        designator.bind();
    }

    @Override
    public void typeCheck() {
        if (!(designator instanceof DesignatorNode)) {
            new errors.GestionErroresTiny().errorTipos("Pasa un \"designador\" al scan");
        }
        if (!designator.getType().equals(new IntTypeNode())) {
            new errors.GestionErroresTiny().errorTipos("El \"designador\" del scan no es de tipo int");
        }
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
}
