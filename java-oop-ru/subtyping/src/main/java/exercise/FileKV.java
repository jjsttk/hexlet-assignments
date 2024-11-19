package exercise;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage, Serializable {
    private final String filePath;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        if (Utils.readFile(filePath).isEmpty()) {
            Utils.writeFile(filePath, Utils.serialize(new HashMap<>(initialData)));
        }
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> data = loadData();
        data.put(key, value);
        saveData(data);
    }

    @Override
    public void unset(String key) {
        Map<String, String> data = loadData();
        data.remove(key);
        saveData(data);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> data = loadData();
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(loadData());
    }

    private Map<String, String> loadData() {
        String serializedData = Utils.readFile(filePath);
        return Utils.deserialize(serializedData);
    }

    private void saveData(Map<String, String> data) {
        String serializedData = Utils.serialize(data);
        Utils.writeFile(filePath, serializedData);
    }
}
// END
