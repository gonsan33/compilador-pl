package ast;

import java.util.ArrayList;

public class StructTypeNode extends TypeNode{
    protected ArrayList<ParameterNode> fields;

    public StructTypeNode(ArrayList<ParameterNode> fields) {
        this.fields = fields;
    }

    public String toString() {
        return "Struct(fields = " + fields + ")";
    }
}
