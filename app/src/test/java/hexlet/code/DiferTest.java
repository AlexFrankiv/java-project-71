package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

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
    void testNestedJsonFiles() throws Exception {
        File json1 = new File("src/test/resources/file1.json");
        File json2 = new File("src/test/resources/file2.json");

        String result = Differ.generate(json1, json2, "stylish");

        assertTrue(result.contains("chars1: [a, b, c]"));
        assertTrue(result.contains("- chars2: [d, e, f]"));
        assertTrue(result.contains("+ chars2: false"));
        assertTrue(result.contains("- checked: false"));
        assertTrue(result.contains("+ checked: true"));
        assertTrue(result.contains("- default: null"));
        assertTrue(result.contains("+ default: [value1, value2]"));
        assertTrue(result.contains("+ obj1: {nestedKey=value, isNested=true}"));
    }
    @Test
    void testNestedYamlFiles() throws Exception {
        File yaml1 = new File("src/test/resources/filepath1.yml");
        File yaml2 = new File("src/test/resources/filepath2.yml");

        String result = Differ.generate(yaml1, yaml2, "stylish");

        assertTrue(result.contains("chars1: [a, b, c]"));
        assertTrue(result.contains("- chars2: [d, e, f]"));
        assertTrue(result.contains("+ chars2: false"));
        assertTrue(result.contains("+ obj1: {nestedKey=value, isNested=true}"));
    }

}
