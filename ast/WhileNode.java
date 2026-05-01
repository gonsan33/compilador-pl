package ast;

public class WhileNode extends ControlSentenceNode{

    public WhileNode(ExpressionNode boolean_expression, BlockNode block) {
        super(boolean_expression, block);
    }

    public String toString() {
        return "While(cond = " + boolean_expression + ", block = " + block + ")";
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder(); 
        str.append("block\n"); 
        str.append("loop\n");
        str.append(this.boolean_expression.generateCode()) ; 
        str.append("i32.eqz\n"); 
        str.append("br_if 1 \n"); 
        str.append(block.generateCode()); 
        str.append("br 0 \n");
        str.append("end \n");
        str.append("end \n");
        return str; 
    }
}
