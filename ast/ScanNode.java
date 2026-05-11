package ast;

public class ScanNode extends SentenceNode {
    protected DesignatorNode designator;

    public ScanNode(DesignatorNode designator) {
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
        designator.typeCheck();
        if (!(designator instanceof DesignatorNode)) {
            new errors.GestionErroresTiny().errorTipos("Pasa un \"designador\" al scan");
        }
        if (!designator.getType().equals(new IntTypeNode())) {
            new errors.GestionErroresTiny().errorTipos("El \"designador\" del scan no es de tipo int");
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        str.append(designator.generateCodeL());
        str.append("  call $read\n");
        str.append("  i32.store\n");
        return str;
    }
}
