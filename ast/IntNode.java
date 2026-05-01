package ast;

public class IntNode extends ExpressionNode {
  private String v;

  public IntNode(String v) {
    this.v = v;
  }

  public String toString() {
    return v;
  }

  @Override
  public void bind() {

  }

  @Override
  public void typeCheck() {

  }

  @Override
  public StringBuilder generateCode() {
    StringBuilder sb = new StringBuilder();
    sb.append("i32.const " + this.v); 
    sb.append("\n");
    return sb;
  }

  @Override
  public TypeNode getType() {
    return new IntTypeNode();
  }
}
