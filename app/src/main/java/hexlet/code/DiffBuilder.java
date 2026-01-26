package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Objects;

public class DiffBuilder {
    public static List<DiffItem> build(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<DiffItem> diffs = new ArrayList<>();

        for (String key : allKeys) {
            boolean inMap1 = map1.containsKey(key);
            boolean inMap2 = map2.containsKey(key);

            if (inMap1 && !inMap2) {
                diffs.add(new DiffItem(key, "removed", map1.get(key)));
            } else if (!inMap1 && inMap2) {
                diffs.add(new DiffItem(key, "added", map2.get(key)));
            } else {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);

                if (Objects.equals(value1, value2)) {
                    diffs.add(new DiffItem(key, "unchanged", value1));
                } else {
                    diffs.add(new DiffItem(key, "changed", value1, value2));
                }
            }
        }

        return diffs;
    }
}
