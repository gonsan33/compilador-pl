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
  public StringBuilder generateCode() {
    StringBuilder str = new StringBuilder();
    str.append("i32.const ").append(this.v ? 1 : 0).append(System.lineSeparator());
    return str;
  }
  @Override
  public TypeNode getType() {
    return new BoolTypeNode(); 
  }  
}