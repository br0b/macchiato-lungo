package interpreter;

import java.util.HashMap;

public class Scope<KeyT, ValueT> {
    // Variables in the current scope.
    private final HashMap<KeyT, ValueT> data;

    public Scope() {
        data = new HashMap<>();
    }

    public ValueT getValue(KeyT key) throws ScopeException {
        if (!isDeclared(key))
            throw new ScopeException();

        return data.get(key);
    }

    public void declare(KeyT key, ValueT valueT) throws ScopeException {
        if (isDeclared(key))
            throw new ScopeException();

        data.put(key, valueT);
    }

    // Assumes variableID is already declared.
    public void assign(KeyT key, ValueT valueT) {
        data.put(key, valueT);
    }

    public void getValues(HashMap<KeyT, ValueT> variables) {
        variables.putAll(this.data);
    }

    public boolean isDeclared(KeyT key) {
        return data.containsKey(key);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
