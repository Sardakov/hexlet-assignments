package exercise;

import java.util.Map;

// BEGIN
public class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage data) {
        Map<String, String> map = data.toMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            data.set(entry.getValue(), entry.getKey());
            data.unset(entry.getKey());
        }
        return data;
    }
}
// END
