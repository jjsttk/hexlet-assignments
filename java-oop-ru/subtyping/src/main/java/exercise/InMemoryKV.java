package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    Map<String, String> dictionaryDB;

    public InMemoryKV(Map<String, String> dictionary) {
        dictionaryDB = new HashMap<>(dictionary);

    }

    @Override
    public void set(String key, String value) {
        dictionaryDB.put(key, value);
    }

    @Override
    public void unset(String key) {
        dictionaryDB.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        System.out.println(dictionaryDB.get(key));
        return dictionaryDB.get(key) == null ? defaultValue : dictionaryDB.get(key);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(dictionaryDB);
    }
}
// END
