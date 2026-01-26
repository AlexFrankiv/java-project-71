package hexlet.code.fornatters;

import hexlet.code.DiffItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<DiffItem> diffs) throws Exception {
        List<String> lines = new ArrayList<>();

        for (DiffItem diff : diffs) {
            switch (diff.getStatus()) {
                case "added":
                    lines.add("Property '" + diff.getKey() + "' was added with value: "
                            + formatValue(diff.getValue()));
                    break;
                case "removed":
                    lines.add("Property '" + diff.getKey() + "' was removed");
                    break;
                case "changed":
                    lines.add("Property '" + diff.getKey() + "' was updated. From "
                            + formatValue(diff.getOldValue()) + " to "
                            + formatValue(diff.getNewValue()));
                    break;
                case "unchanged":
                    break;
                default: throw new Exception("Unknown status");
            }
        }

        return String.join("\n", lines);
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        } else {
            return String.valueOf(value);
        }
    }
}
