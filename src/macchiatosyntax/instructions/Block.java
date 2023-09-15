package macchiatosyntax.instructions;

import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.MacchiatoSyntax;
import macchiatosyntax.MacchiatoSyntaxIterator;
import macchiatosyntax.declarations.Declaration;

import java.util.ArrayDeque;

public class Block extends ComplexInstruction {
    protected final ArrayDeque<Declaration> declarations;

    // This instruction is used to step out of scope.
    // It isn't explicitly added to the program when the program is constructed by the user.
    protected static class EndBlock extends SimpleInstruction {
        @Override
        public void run(CallStack callStack, Memory memory) {
            memory.stepOutOfScope();
        }

        @Override
        public String printSyntax() {
            return "end block";
        }
    }

    protected class BlockIterator implements MacchiatoSyntaxIterator {
        protected final Declaration[] declarationsArray;

        protected final Instruction[] instructionsArray;

        // Current position in the block. Both the declarations and instructions count.
        protected int position;

        public BlockIterator() {
            declarationsArray = declarations.toArray(new Declaration[0]);

            instructionsArray = new Instruction[instructions.size() + 1];
            System.arraycopy(instructions.toArray(new Instruction[]{}),
                           0,
                             instructionsArray,
                          0,
                             instructions.size());

            // Add the special "end block" instruction to the end of instructions.
            instructionsArray[instructionsArray.length - 1] = new EndBlock();

            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < declarationsArray.length + instructionsArray.length;
        }

        @Override
        public MacchiatoSyntax next() {
            if (!hasNext())
                return null;

            MacchiatoSyntax macchiatoSyntax = peek();

            position++;

            return macchiatoSyntax;
        }

        @Override
        public MacchiatoSyntax peek() {
            if (!hasNext())
                return null;

            if (position < declarationsArray.length) {
                return declarationsArray[position];
            }
            else {
                return instructionsArray[position - declarationsArray.length];
            }
        }
    }

    public Block(ArrayDeque<Declaration> declarations, ArrayDeque<Instruction> instructions) {
        this.declarations = declarations;
        this.instructions = instructions;
    }

    @Override
    public MacchiatoSyntaxIterator iterator(Memory memory) {
        return new BlockIterator();
    }

    // A line containing "begin block" counts as an instruction.
    @Override
    public void run(CallStack callStack, Memory memory) {
        assert callStack != null;

        // This is the only instruction that can create a new scope.
        memory.newScope();

        callStack.push(iterator(memory));
    }

    @Override
    public String printSyntax() {
        return "begin block";
    }
}
