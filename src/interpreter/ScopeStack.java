package interpreter;

import interpreter.dataprinter.DataPrinter;

import java.util.*;

/**
 * This is a memory, that the macchiato instructions and declarations have access to.
 */
public class ScopeStack<KeyT, ValueT> {
    // Stack of scopes/
    private final ArrayDeque<Scope<KeyT, ValueT>> scopes;

    public ScopeStack() {
        scopes = new ArrayDeque<>();
    }

    public ValueT getValue(KeyT key) throws ScopeException {
        Scope<KeyT, ValueT> scope = getScope(key);
        return scope.getValue(key);
    }

    public void declare(KeyT key, ValueT value) throws ScopeException {
        if (isDeclaredInCurrentScope(key))
            throw new ScopeException();

        assert !scopes.isEmpty();

        scopes.peek().declare(key, value);
    }

    public void assign(KeyT key, ValueT value) throws ScopeException {
        getScope(key).assign(key, value);
    }

    public String display(DataPrinter<KeyT, ValueT> dataPrinter, int depth) {
        if (depth >= scopes.size()) {
            return "The current nesting depth is less then " + depth + ".";
        }
        else if (isMemoryEmpty()) {
            return "No variables in current scope";
        }

        StringBuilder memoryString = new StringBuilder("Current memory state:\n");
        HashMap<KeyT, ValueT> currentData = getData(depth);

        for (Map.Entry<KeyT, ValueT> variable : currentData.entrySet())
            memoryString.append(dataPrinter.print(variable)).append("\n");

        return memoryString.toString().strip();
    }

    public String display(DataPrinter<KeyT, ValueT> dataPrinter) {
        return display(dataPrinter, 0);
    }

    public String dump(DataPrinter<KeyT, ValueT> dataPrinter) {
        if (isMemoryEmpty()) {
            return "No variables in current scope.";
        }

        StringBuilder memoryString = new StringBuilder();

        HashMap<KeyT, ValueT> currentVariables = getData();

        for (Map.Entry<KeyT, ValueT> variable : currentVariables.entrySet())
            memoryString.append(dataPrinter.print(variable)).append("\n");

        return memoryString.toString().strip();
    }

    private Scope<KeyT, ValueT> getScope(KeyT key) throws ScopeException {
        for (Scope<KeyT, ValueT> scope : scopes) {
            if (scope.isDeclared(key))
                return scope;
        }

        throw new ScopeException();
    }

    public void newScope() {
        scopes.push(new Scope<>());
    }

    public void stepOutOfScope() {
        scopes.pop();
    }

    private HashMap<KeyT, ValueT> getData(int depth) {
        assert depth <= scopes.size();

        HashMap<KeyT, ValueT> valuesInCurrentScope = new HashMap<>();
        Iterator<Scope<KeyT, ValueT>> memoryIterator = scopes.descendingIterator();

        for (int i = 0; i < scopes.size() - depth; i++) {
            Scope<KeyT, ValueT> scope = memoryIterator.next();
            scope.getValues(valuesInCurrentScope);
        }

        return valuesInCurrentScope;
    }

    public HashMap<KeyT, ValueT> getData() {
        return getData(0);
    }

    private boolean isDeclaredInCurrentScope(KeyT key) {
        if (scopes.isEmpty()) {
            return false;
        }
        else return scopes.peek().isDeclared(key);
    }

    private boolean isMemoryEmpty() {
        for (Scope<KeyT, ValueT> scope : scopes) {
            if (!scope.isEmpty())
                return false;
        }

        return true;
    }
}