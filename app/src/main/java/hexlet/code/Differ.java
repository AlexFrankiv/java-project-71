package hexlet.code;

import java.util.*;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2){
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

                if (Objects.equals(valueMap1,valueMap2)) {
                    result.add("  " + key + valueMap1);
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
