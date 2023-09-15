package macchiatosyntax.instructions;

public class MainProgramBlockBuilder extends AbstractBlockBuilder<MainProgramBlock, MainProgramBlockBuilder> {
    @Override
    protected MainProgramBlockBuilder getThis() {
        return this;
    }

    @Override
    public MainProgramBlock build() {
        return new MainProgramBlock(declarations, instructions);
    }
}
