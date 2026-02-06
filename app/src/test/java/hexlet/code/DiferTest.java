package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DiferTest {
    private static final String JSON_FILE1 = "src/test/resources/file1.json";
    private static final String JSON_FILE2 = "src/test/resources/file2.json";
    private static final String YAML_FILE1 = "src/test/resources/filepath1.yml";
    private static final String YAML_FILE2 = "src/test/resources/filepath2.yml";

    private static String json1Content;
    private static String json2Content;
    private static String yaml1Content;
    private static String yaml2Content;

    @BeforeAll
    static void setUp() throws Exception {
        json1Content = readFile(JSON_FILE1);
        json2Content = readFile(JSON_FILE2);
        yaml1Content = readFile(YAML_FILE1);
        yaml2Content = readFile(YAML_FILE2);

        assertFalse(json2Content.isEmpty());
        assertFalse(yaml1Content.isEmpty());
        assertFalse(yaml2Content.isEmpty());
    }

    @Test
    void testJsonToStylish() throws Exception {
        String expected = readFixture("expected/stylish_json.txt");
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "stylish");
        assertEquals(expected, result, "JSON to stylish format failed");
    }

    @Test
    void testJsonToPlain() throws Exception {
        String expected = readFixture("expected/plain_json.txt");
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "plain");
        assertEquals(expected, result, "JSON to plain format failed");
    }

    @Test
    void testJsonToJson() throws Exception {
        String expected = readFixture("expected/json_json.json");
        String result = Differ.generate(JSON_FILE1, JSON_FILE2, "json");
        assertEquals(expected, result, "JSON to json format failed");
    }

    @Test
    void testJsonDefaultFormat() throws Exception {
        String expectedStylish = readFixture("expected/stylish_json.txt");
        String resultDefault = Differ.generate(JSON_FILE1, JSON_FILE2);
        assertEquals(expectedStylish, resultDefault, "Default format should be stylish for JSON");
    }

    @Test
    void testYamlToStylish() throws Exception {
        String expected = readFixture("expected/stylish_yaml.txt");
        String result = Differ.generate(YAML_FILE1, YAML_FILE2, "stylish");
        assertEquals(expected, result, "YAML to stylish format failed");
    }

    @Test
    void testYamlToPlain() throws Exception {
        String expected = readFixture("expected/plain_yaml.txt");
        String result = Differ.generate(YAML_FILE1, YAML_FILE2, "plain");
        assertEquals(expected, result, "YAML to plain format failed");
    }

    @Test
    void testYamlToJson() throws Exception {
        String expected = readFixture("expected/json_yaml.json");
        String result = Differ.generate(YAML_FILE1, YAML_FILE2, "json");
        assertEquals(expected, result, "YAML to json format failed");
    }

    @Test
    void testYamlDefaultFormat() throws Exception {
        String expectedStylish = readFixture("expected/stylish_yaml.txt");
        String resultDefault = Differ.generate(YAML_FILE1, YAML_FILE2);
        assertEquals(expectedStylish, resultDefault, "Default format should be stylish for YAML");
    }
    @Test
    void testSameDataDifferentFormat() throws Exception {

        String jsonResult = Differ.generate(JSON_FILE1, JSON_FILE2, "stylish");
        String yamlResult = Differ.generate(YAML_FILE1, YAML_FILE2, "stylish");

        assertEquals(jsonResult, yamlResult,
                "Same data in JSON and YAML should produce same diff");
    }
    private String readFixture(String fileName) throws Exception {
        String content = Files.readString(
                Paths.get("src/test/resources/" + fileName)
        );
        return content.trim();
    }
    private static String readFile(String filePath) throws Exception {
        return Files.readString(Paths.get(filePath)).trim();
    }
}
