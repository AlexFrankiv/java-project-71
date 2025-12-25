package hexlet.code;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Differ {
    public static String generate(File file1, File file2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(file1.getPath());
        Map<String, Object> map2 = Parser.parse(file2.getPath());

        return generate(map1, map2);
    }
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
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
                    result.add("  " + key + ": " + valueMap1);
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
