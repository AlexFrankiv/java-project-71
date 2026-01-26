package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiferTest {
    private static final String FILE1_JSON = "src/test/resources/file1.json";
    private static final String FILE2_JSON = "src/test/resources/file2.json";
    private static final String FILE1_YML = "src/test/resources/filepath1.yml";
    private static final String FILE2_YML = "src/test/resources/filepath2.yml";
    private static final int MIN_RESULT_LENGTH = 10;
    private static final int MIN_JSON_LENGTH = 2;

    @Test
    void testGenerateStylishJson() throws Exception {
        String actual = Differ.generate(FILE1_JSON, FILE2_JSON, "stylish");

        assertTrue(actual.contains("+ ") || actual.contains("- "), "Should contain +/- markers");
        assertTrue(actual.contains(":"), "Should contain colon separator");

        assertTrue(actual.length() > MIN_RESULT_LENGTH, "Result should not be empty");
    }
    @Test
    void testGeneratePlainYml() throws Exception {
        String actual = Differ.generate(FILE1_YML, FILE2_YML, "plain");

        assertTrue(actual.contains("was updated") || actual.contains("was added")
                || actual.contains("was removed"), "Should contain change descriptions");

        assertTrue(actual.contains("Property ") || actual.toLowerCase().contains("property "),
                "Should reference properties");
    }

    @Test
    void testGenerateJsonFormatYml() throws Exception {
        String actual = Differ.generate(FILE1_YML, FILE2_YML, "json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(actual);
        assertTrue(json.isObject(), "Result should be a valid JSON object");

        assertTrue(json.size() > 0 || json.toString().length() > MIN_JSON_LENGTH,
                "JSON should not be empty");
    }
}
