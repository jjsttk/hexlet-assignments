package exercise;

import java.util.Map;

// BEGIN
public class App {


    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> storageCopy = storage.toMap();
        for (var entry : storageCopy.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
// END
