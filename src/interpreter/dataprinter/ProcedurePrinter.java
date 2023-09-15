package interpreter.dataprinter;

import macchiatosyntax.Procedure;

import java.util.Map;

public class ProcedurePrinter implements DataPrinter<String, Procedure> {
    @Override
    public String print(Map.Entry<String, Procedure> data) {
        return data.getKey() + data.getValue().printProcedureParameters();
    }
}
