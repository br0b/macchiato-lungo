package interpreter;

import macchiatosyntax.MacchiatoSyntax;
import macchiatosyntax.MacchiatoSyntaxIterator;
import macchiatosyntax.MacchiatoSyntaxException;

import java.util.ArrayDeque;

/**
 * This is a stack that holds syntax iterators. It helps to return from a nested instruction/declaration
 * to an outer instruction.
 */
public class CallStack {
    private final ArrayDeque<MacchiatoSyntaxIterator> macchiatoSyntaxIterators;
    private final Memory memory;

    public CallStack(Memory memory) {
        macchiatoSyntaxIterators = new ArrayDeque<>();
        this.memory = memory;
    }

    public void push(MacchiatoSyntaxIterator macchiatoSyntaxIterator) {
        macchiatoSyntaxIterators.push(macchiatoSyntaxIterator);
    }

    public boolean hasNext() {
        return !macchiatoSyntaxIterators.isEmpty();
    }

    public void next() throws MacchiatoSyntaxException {
        if (!hasNext())
            return;

        assert macchiatoSyntaxIterators.peek() != null;

        // Run the next line of program.
        peekStack().next().run(this, memory);

        while (!macchiatoSyntaxIterators.isEmpty() && !peekStack().hasNext()) {
            macchiatoSyntaxIterators.pop();
        }
    }

    private MacchiatoSyntaxIterator peekStack() {
        return macchiatoSyntaxIterators.peek();
    }

    public MacchiatoSyntax peekNextLine() {
        if (!hasNext())
            return null;

        assert peekStack() != null;

        return peekStack().peek();
    }

    public void clear() {
        macchiatoSyntaxIterators.clear();
    }
}
