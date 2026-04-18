package ast;

public class BooleanNode extends ExpressionNode {
  private Boolean v;
  public BooleanNode(Boolean v) {
   this.v = v;   
  } 
  public Boolean value(){
    return v;
  }
  public String toString() {
      if (v)return "true";
      else return "false";
  }
  @Override
  public void bind() {
    
  }
  @Override
  public void typeCheck() {
    
  }
  @Override
  public void generateCode() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
  }
  @Override
  public TypeNode getType() {
    return new BoolTypeNode(); 
  }  
}