package ast;

public class NewNode implements ASTNode {

    protected TypeNode type ; 
    public NewNode(TypeNode type ) {
        this.type = type;
    }
    @Override
    public void bind() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bind'");
    }

    @Override
    public void typeCheck() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'typeCheck'");
    }

    @Override
    public void generateCode() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
    }
    
}
