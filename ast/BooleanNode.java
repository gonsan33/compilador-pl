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
}