package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static String getFileFormat(String filePath) throws Exception {
        if (filePath.endsWith(".json")) {
            return "json";
        } else if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            return "yaml";
        } else {
            throw new Exception("Unsupported file format: " + filePath);
        }
    }

    public static Map<String, Object> parse(String filePath) throws Exception {
        String format = getFileFormat(filePath);
        String content = Files.readString(Paths.get(filePath));

        return parseContent(content, format);
    }

    public static Map<String, Object> parseContent(String content, String format) throws Exception {
        ObjectMapper mapper;

        if ("json".equals(format)) {
            mapper = new ObjectMapper();
        } else if ("yaml".equals(format)) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new IllegalArgumentException("Unsupported format: " + format);
        }

        return mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
    }
}
