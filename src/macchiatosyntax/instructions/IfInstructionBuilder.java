package macchiatosyntax.instructions;

public class IfInstructionBuilder extends AbstractIfInstructionBuilder<IfInstruction, IfInstructionBuilder> {
    public IfInstructionBuilder() {
        super();
    }

    @Override
    protected IfInstructionBuilder getThis() {
        return this;
    }

    @Override
    public IfInstruction build() throws Exception {
        if (!areNecessaryFieldsSet()) {
            throw new Exception("Set all necessary fields for if instruction.");
        }

        return new IfInstruction(ifCondition, firstExpression, secondExpression, instructions);
    }
}
