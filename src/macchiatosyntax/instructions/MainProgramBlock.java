package macchiatosyntax.instructions;

import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.MacchiatoSyntaxIterator;
import macchiatosyntax.declarations.Declaration;

import java.util.ArrayDeque;

/**
 * The main program block is special, because we don't want to step out of the root memory scope after the program
 * is finished.
 */
public class MainProgramBlock extends Block {
    public MainProgramBlock(ArrayDeque<Declaration> declarations,
                            ArrayDeque<Instruction> instructions) {
        super(declarations, instructions);
    }

    @Override
    public MacchiatoSyntaxIterator iterator(Memory memory) {
        return new MainBlockIterator();
    }

    private class MainBlockIterator extends BlockIterator {

        public MainBlockIterator() {
            super();
            instructionsArray[instructionsArray.length - 1] = new EndMainBlock();
        }

    }

    private static class EndMainBlock extends EndBlock {

        @Override
        public void run(CallStack callStack, Memory memory) {

        }
    }
}
