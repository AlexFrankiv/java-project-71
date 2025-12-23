package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiferTest {
    @Test
    void testSameFiles() {
        Map<String, Object> map1 = Map.of("host", "hexlet.io", "timeout", 50);
        Map<String, Object> map2 = Map.of("host", "hexlet.io", "timeout", 50);
        String result = Differ.generate(map1, map2);
        assertEquals("{\n host: hexlet.io\n timeout: 50\n}", result);
    }

    @Test
    void testDifferentFiles() {
        Map<String, Object> map1 = Map.of("host", "hexlet.io", "timeout", 50);
        Map<String, Object> map2 = Map.of("host", "hexlet.io", "timeout", 20, "verbose", true);
        String result = Differ.generate(map1, map2);
        assertTrue(result.contains("- timeout: 50"));
        assertTrue(result.contains("+ timeout: 20"));
        assertTrue(result.contains("+ verbose: true"));
    }
}
