import expressions.Addition;
import expressions.Constant;
import expressions.Subtraction;
import expressions.Variable;
import interpreter.Debugger;
import macchiatosyntax.Program;
import macchiatosyntax.instructions.BlockBuilder;
import macchiatosyntax.instructions.MainProgramBlock;
import macchiatosyntax.instructions.MainProgramBlockBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example program from the problem specification. It can be run using an Interpreter or Debugger.
        MainProgramBlock exampleProgramBlock;

        try {
            exampleProgramBlock = new MainProgramBlockBuilder()
                    .declareVariable('x', Constant.of(101))
                    .declareVariable('y', Constant.of(1))
                    .declareProcedure("out", List.of('a'), new BlockBuilder()
                            .print(Addition.of(Variable.named('a'), Variable.named('x')))
                            .build()
                    )
                    .assign('x', Subtraction.of(Variable.named('x'), Variable.named('y')))
                    .invoke("out", List.of(Variable.named('x')))
                    .invoke("out", List.of(Constant.of(100)))
                    .block(new BlockBuilder()
                            .declareVariable('x', Constant.of(10))
                            .invoke("out", List.of(Constant.of(100)))
                            .build()
                    )
                    .build();
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        new Debugger().debug(new Program(exampleProgramBlock));
    }
}
