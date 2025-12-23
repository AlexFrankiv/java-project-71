package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Differ {
    public static String generate(File filePath1, File filePath2, String format) throws Exception {
        String content1 = Files.readString(filePath1.toPath());
        String content2 = Files.readString(filePath2.toPath());

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(content1, new TypeReference<Map<String, Object>>() {});;
        Map<String, Object> map2 = mapper.readValue(content2, new TypeReference<Map<String, Object>>() {});

        return generate(map1, map2);
    }

    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<String> result = new ArrayList<>();
        for (var key : allKeys) {
            boolean keyInMap1 = map1.containsKey(key);
            boolean keyInMap2 = map2.containsKey(key);

            if (keyInMap1 && !keyInMap2) {
                result.add("- " + key + ": " + map1.get(key));
            } else if (!keyInMap1 && keyInMap2) {
                result.add("+ " + key + ": " + map2.get(key));
            } else {
                Object valueMap1 = map1.get(key);
                Object valueMap2 = map2.get(key);

                if (Objects.equals(valueMap1, valueMap2)) {
                    result.add(" " + key + ": "+ valueMap1);
                } else {
                    result.add("- " + key + ": " + valueMap1);
                    result.add("+ " + key + ": " + valueMap2);
                }
            }
        }
        StringBuilder resultMethod = new StringBuilder("{\n");
        for (String line : result) {
            resultMethod.append(line).append("\n");
        }
        resultMethod.append("}");

        return resultMethod.toString();
    }
}
