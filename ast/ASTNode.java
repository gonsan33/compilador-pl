package ast;

import java.util.ArrayList;
import java.util.List;

public interface ASTNode {
    public String toString();

    public void bind();

    public void typeCheck();

    public StringBuilder generateCode();

    public default int asignarDelta(int acum) {
        return acum;
    }

    public default List<ASTNode> hijos() {
        return new ArrayList<>();
    }

}
