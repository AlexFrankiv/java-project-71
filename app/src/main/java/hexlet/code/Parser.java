package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws Exception {
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
