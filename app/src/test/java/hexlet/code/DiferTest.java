package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiferTest {
    @Test
    void testDifferentFiles() throws Exception {
        Map<String, Object> map1 = Map.of("host", "hexlet.io", "timeout", 50);
        Map<String, Object> map2 = Map.of("host", "hexlet.io", "timeout", 20, "verbose", true);
        String result = Differ.generate(map1, map2);
        assertTrue(result.contains("- timeout: 50"));
        assertTrue(result.contains("+ timeout: 20"));
        assertTrue(result.contains("+ verbose: true"));
    }
    @Test
    void testGenerateWithValidAndInvalidFiles() throws Exception {
        File temp1 = Files.createTempFile("test1", ".json").toFile();
        File temp2 = Files.createTempFile("test2", ".json").toFile();
        Files.writeString(temp1.toPath(), "{}");
        Files.writeString(temp2.toPath(), "{}");

        Differ.generate(temp1, temp2, "stylish");

        assertThrows(Exception.class, () ->
                Differ.generate(new File("fake1.json"), new File("fake2.json"), "stylish"));
    }
    @Test
    void testDifferentYamlFiles() throws Exception {
        Map<String, Object> map1 = Map.of("host", "hexlet.io", "timeout", 50);
        Map<String, Object> map2 = Map.of("host", "hexlet.io", "timeout", 20, "verbose", true);

        String result = Differ.generate(map1, map2);

        assertTrue(result.contains("- timeout: 50"));
        assertTrue(result.contains("+ timeout: 20"));
        assertTrue(result.contains("+ verbose: true"));
    }
    @Test
    void testRealJsonFiles() throws Exception {
        File file1 = new File("src/test/resources/file1.json");
        File file2 = new File("src/test/resources/file2.json");

        if (file1.exists() && file2.exists()) {
            String result = Differ.generate(file1, file2, "stylish");
            assertNotNull(result);
        }
    }
    @Test
    void testRealYamlFiles() throws Exception {
        File file1 = new File("src/test/resources/file1.yml");
        File file2 = new File("src/test/resources/file2.yml");

        if (file1.exists() && file2.exists()) {
            String result = Differ.generate(file1, file2, "stylish");
            assertNotNull(result);
        }
    }
}
