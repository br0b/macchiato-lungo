package macchiatosyntax;

import interpreter.CallStack;
import interpreter.Memory;
import macchiatosyntax.instructions.MainProgramBlock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Program {
    // Memory that the instructions of this program can use.
    private final Memory memory;
    // A stack of current syntax iterators.
    private final CallStack callStack;

    public Program(MainProgramBlock mainProgramBlock) {
        memory = new Memory();
        callStack = new CallStack(memory);

        // Push the first iterator onto the call stack.
        callStack.push(new ProgramIterator(mainProgramBlock));
    }

    public void memoryDump(String filePath) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
        bufferedWriter.write(memory.dump());
        bufferedWriter.close();
    }

    private static class ProgramIterator implements MacchiatoSyntaxIterator {
        private final MainProgramBlock mainProgramBlock;
        private boolean programHasBeenStarted;

        public ProgramIterator(MainProgramBlock mainProgramBlock) {
            this.mainProgramBlock = mainProgramBlock;
            programHasBeenStarted = false;
        }

        @Override
        public boolean hasNext() {
            return !programHasBeenStarted;
        }

        @Override
        public MacchiatoSyntax next() {
            if (programHasBeenStarted)
                return null;

            programHasBeenStarted = true;
            return mainProgramBlock;
        }

        @Override
        public MacchiatoSyntax peek() {
            if (programHasBeenStarted)
                return null;

            return mainProgramBlock;
        }
    }

    // Run the next numOfSteps instructions.
    public void step(int numOfSteps) {
        if (isFinished()) {
            System.out.println("The program is already finished.");
            return;
        }

        for (int i = 0; i < numOfSteps; i++) {
            if (isFinished()) {
                System.out.println("The program finished in less than " + numOfSteps + " steps.\n");
                finish();
                return;
            }

            try {
                callStack.next();
            }
            catch (MacchiatoSyntaxException macchiatoSyntaxException) {
                abortProgram(macchiatoSyntaxException);
                return;
            }
        }

        if (isFinished())
            finish();
        else
            System.out.println(callStack.peekNextLine().printSyntax());
    }

    public void continueProgram() {
        if (isFinished()) {
            System.out.println("The program is already finished.");
            return;
        }

        try {
            while (callStack.hasNext()) {
                callStack.next();
            }

            finish();
        }
        catch (MacchiatoSyntaxException e) {
            abortProgram(e);
        }
    }

    public void display(int depth) {
        System.out.println(memory.displayVariables(depth));
    }

    public boolean isFinished() {
        return !callStack.hasNext();
    }

    private void finish() {
        System.out.println("Program executed successfully.\n" + memory.displayVariables());
    }

    private void abortProgram(MacchiatoSyntaxException programError) {
        callStack.clear();

        System.out.println("An error has occurred, exiting.\n + " +
                "Error caused by <" + programError.getMessage() + ">.\n" +
                memory.displayVariables());
    }
}