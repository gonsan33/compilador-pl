package ast;

import java.util.ArrayList;
import bind.SymbolTable;

public class ProgramNode extends SentenceNode {
    protected ArrayList<SentenceNode> instructions;

    public ProgramNode(ArrayList<SentenceNode> instructions) {
        this.instructions = instructions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Program(");
        for (int i = 0; i < instructions.size(); i++) {
            if (i > 0)
                sb.append(", ");
            sb.append(instructions.get(i));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void bind() {
        SymbolTable.reset();
        SymbolTable st = SymbolTable.getSymbolTable();
        st.openScope();
        for (SentenceNode instruction : instructions) {
            instruction.bind();
        }
        st.closeScope();
    }

    @Override
    public void typeCheck() {
        for (SentenceNode instruction : instructions) {
            instruction.typeCheck();
        }
    }

    @Override
    public StringBuilder generateCode() {
        StringBuilder str = new StringBuilder();
        str.append("(module\n");
        str.append("  (type $_sig_i32i32i32 (func (param i32 i32 i32) ))\n");
        str.append("  (type $_sig_i32ri32 (func (param i32) (result i32)))\n");
        str.append("  (type $_sig_i32 (func (param i32)))\n");
        str.append("  (type $_sig_ri32 (func (result i32)))\n");
        str.append("  (type $_sig_void (func ))\n");
        str.append("  (import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))\n");
        str.append("  (import \"runtime\" \"print\" (func $print (type $_sig_i32)))\n");
        str.append("  (import \"runtime\" \"read\" (func $read (type $_sig_ri32)))\n");
        str.append("  (memory 2000)\n");
        str.append("  (global $SP (mut i32) (i32.const 0)) ;; start of stack\n");
        str.append("  (global $MP (mut i32) (i32.const 0)) ;; mark pointer\n");
        str.append("  (global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\n");
        str.append("  (start $main)\n");

        str.append("(func $reserveStack (param $size i32) (result i32)\n");
        str.append("  global.get $MP\n");
        str.append("  global.get $SP\n");
        str.append("  global.set $MP\n");
        str.append("  global.get $SP\n");
        str.append("  local.get $size\n");
        str.append("  i32.add\n");
        str.append("  global.set $SP\n");
        str.append("  global.get $SP\n");
        str.append("  global.get $NP\n");
        str.append("  i32.gt_u\n");
        str.append("  if\n");
        str.append("    i32.const 3\n");
        str.append("    call $exception\n");
        str.append("  end\n");
        str.append(")\n");

        str.append("(func $freeStack\n");
        str.append("  global.get $MP\n");
        str.append("  global.set $SP\n");
        str.append("  global.get $MP\n");
        str.append("  i32.load\n");
        str.append("  global.set $MP\n");
        str.append(")\n");

        str.append(
                "(func $reserveHeap (param $size i32) (result i32)\n" +
                        "  global.get $NP\n" +
                        "  global.get $NP\n" +
                        "  local.get $size\n" +
                        "  i32.add\n" +
                        "  global.set $NP\n" +
                        "  global.get $NP\n" +
                        "  global.get $SP\n" +
                        "  i32.lt_u\n" +
                        "  if\n" +
                        "    i32.const 4\n" +
                        "    call $exception\n" +
                        "  end\n" +
                        "  global.get $NP\n" +
                        ")\n\n");

        str.append("(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest\n");
        str.append("  (param $src i32)\n");
        str.append("  (param $dest i32)\n");
        str.append("  (param $n i32)\n");
        str.append("  block\n");
        str.append("    loop\n");
        str.append("      local.get $n\n");
        str.append("      i32.eqz\n");
        str.append("      br_if 1\n");
        str.append("      local.get $n\n");
        str.append("      i32.const 1\n");
        str.append("      i32.sub\n");
        str.append("      local.set $n\n");
        str.append("      local.get $dest\n");
        str.append("      local.get $src\n");
        str.append("      i32.load\n");
        str.append("      i32.store\n");
        str.append("      local.get $dest\n");
        str.append("      i32.const 4\n");
        str.append("      i32.add\n");
        str.append("      local.set $dest\n");
        str.append("      local.get $src\n");
        str.append("      i32.const 4\n");
        str.append("      i32.add\n");
        str.append("      local.set $src\n");
        str.append("      br 0\n");
        str.append("    end\n");
        str.append("  end\n");
        str.append(")\n");

        for (SentenceNode instruction : instructions) {
            str.append(instruction.generateCode());
        }

        str.append(")\n");

        return str;
    }

}
