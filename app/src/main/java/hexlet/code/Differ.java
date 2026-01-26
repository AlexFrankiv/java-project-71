package hexlet.code;

import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Objects;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2, String format) throws Exception {
        List<DiffItem> diffs = collectDiffs(map1, map2);

        return Formatter.format(diffs, format);
    }

    public static String generate(File file1, File file2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(file1.getPath());
        Map<String, Object> map2 = Parser.parse(file2.getPath());

        return generate(map1, map2, format);
    }

    public static String generate(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        return generate(map1, map2, "stylish");
    }
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(new File(filepath1), new File(filepath2), "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        return generate(new File(filepath1), new File(filepath2), format);
    }

    private static List<DiffItem> collectDiffs(Map<String, Object> map1, Map<String, Object> map2) {
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
