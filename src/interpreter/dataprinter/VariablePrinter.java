package interpreter.dataprinter;

import java.util.Map;

public class VariablePrinter implements DataPrinter<Character, Integer> {
    @Override
    public String print(Map.Entry<Character, Integer> data) {
        return data.getKey().toString() + " = " + data.getValue().toString();
    }
}
