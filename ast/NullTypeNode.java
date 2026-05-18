package ast;

public class NullTypeNode extends TypeNode {
    public NullTypeNode() {
    }

    @Override
    public String toString() {
        return "NullType";
    }


    @Override
    public void bind() {
        
    }

    @Override
    public void typeCheck() {
        
    }

    @Override
    public boolean equals(TypeNode other) {
        return other instanceof NullTypeNode;
    }

    @Override
    public int size() {
        return 0; 
    }

    @Override
    public String typeName() {
        return "null";
    }
    
}
