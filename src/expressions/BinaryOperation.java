package expressions;

public abstract class BinaryOperation extends Expression {
    protected final Expression left;
    protected final Expression right;

    public BinaryOperation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    abstract protected char operator();

    String putInBracesIfNeeded(Expression argument) {
        if (this.evaluationPriority() < argument.evaluationPriority())
            return "(" + argument + ")";
        else
            return argument.toString();
    }

    @Override
    public String toString() {
        return putInBracesIfNeeded(left) + " " + operator() + " " + putInBracesIfNeeded(right);
    }
}
