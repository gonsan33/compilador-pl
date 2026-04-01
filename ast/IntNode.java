package ast;

public class IntNode extends ExpressionNode {
  private String v;
  public IntNode(String v) {
   this.v = v;   
  } 
  public String toString() {return v;}  
}
