package exercise;

import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();
    private static Path filepath2 = Paths.get("src/test/resources/file2").toAbsolutePath().normalize();
    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
        Files.deleteIfExists(filepath2);
        Files.createFile(filepath2);
    }

    @Test
    void testFileKV() throws IOException {
        // Инициализация базы данных
        FileKV storage = new FileKV(filepath2.toString(), Map.of("key", "value"));
        assertThat(storage.get("key", "default")).isEqualTo("value");
        assertThat(storage.get("unknown", "default")).isEqualTo("default");

    }

    // BEGIN
    @Test
    void testInitFromFile() throws IOException {
        // Инициализация базы данных

        FileKV storage = new FileKV(filepath2.toString(), Map.of("key", "value"));
        storage.set("key2", "value2");

        FileKV storage2 = new FileKV(filepath2.toString(), Map.of());
        assertThat(storage2.get("key", "default")).isEqualTo("value");
        assertThat(storage2.get("key2", "default")).isEqualTo("value2");
    }
    // END
}
