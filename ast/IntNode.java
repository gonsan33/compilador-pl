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
  public void generateCode() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'generateCode'");
  }

  @Override
  public TypeNode getType() {
    return new IntTypeNode();
  }
}
