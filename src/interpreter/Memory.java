package interpreter;

import interpreter.dataprinter.ProcedurePrinter;
import interpreter.dataprinter.VariablePrinter;
import macchiatosyntax.Procedure;

public class Memory {
    private final ScopeStack<Character, Integer> variables;
    private final ScopeStack<String, Procedure> procedures;

    public Memory() {
        variables = new ScopeStack<>();
        procedures = new ScopeStack<>();
    }

    public int getVariableValue(char variableID) throws ScopeException {
        return variables.getValue(variableID);
    }

    public Procedure getProcedure(String name) throws ScopeException {
        return procedures.getValue(name);
    }

    public void newScope() {
        variables.newScope();
        procedures.newScope();
    }

    public void stepOutOfScope() {
        variables.stepOutOfScope();
        procedures.stepOutOfScope();
    }

    public void declareVariable(char variableID, int value) throws ScopeException {
        variables.declare(variableID, value);
    }

    public void declareProcedure(String procedureName, Procedure procedure) throws ScopeException {
        procedures.declare(procedureName, procedure);
    }

    public void assignVariable(char variableID, int value) throws ScopeException {
        variables.assign(variableID, value);
    }

    public String displayVariables() {
        return variables.display(new VariablePrinter());
    }

    public String displayVariables(int depth) {
        return variables.display(new VariablePrinter(), depth);
    }

    public String dump() {
        return "Procedures:\n" + procedures.dump(new ProcedurePrinter()) +
                "\nVariables:\n" + variables.dump(new VariablePrinter());
    }
}
