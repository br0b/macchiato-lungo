package macchiatosyntax.instructions;

public class BlockBuilder extends AbstractBlockBuilder<Block, BlockBuilder> {
    @Override
    protected BlockBuilder getThis() {
        return this;
    }

    @Override
    public Block build() {
        return new Block(declarations, instructions);
    }
}
