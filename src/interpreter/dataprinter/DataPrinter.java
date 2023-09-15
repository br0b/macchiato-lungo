package interpreter.dataprinter;

import java.util.Map;

public interface DataPrinter<KeyT, ValueT> {
    String print(Map.Entry<KeyT, ValueT> data);
}
