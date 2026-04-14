package ast;

import java.sql.Struct;
import java.util.ArrayList;

public class StructTypeNode extends TypeNode{
    protected ArrayList<ParameterNode> fields;

    public StructTypeNode(ArrayList<ParameterNode> fields) {
        this.fields = fields;
    }

    public String toString() {
        return "Struct(fields = " + fields + ")";
    }

    @Override
    public void bind() {
        for (ParameterNode field : fields) {
            field.getType().bind();
        }
    }

    @Override
    public void typeCheck() {
        for( ParameterNode p : fields) p.typeCheck();
    }

    @Override
    public boolean equals(TypeNode other) {
        if(other.getClass().equals(this.getClass())) {
            StructTypeNode otherStruct = (StructTypeNode) other; 
            if(otherStruct.fields.size() != this.fields.size()) return false; 

            for(int i = 0; i < this.fields.size(); ++i) {
                if(!this.fields.get(i).equals(otherStruct.fields.get(i))) return false; 
            }
            
            return true; 
        }
        return false; 
    }

    @Override
    public int size() {
        int res = 0; 
        for( ParameterNode p : fields) res+= p.getType().size();
        return res; 
    }
}
