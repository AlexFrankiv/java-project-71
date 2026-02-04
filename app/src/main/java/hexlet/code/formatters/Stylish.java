package hexlet.code.formatters;

import hexlet.code.DiffItem;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String format(List<DiffItem> diffs) throws Exception {
        StringBuilder result = new StringBuilder("{\n");

        for (DiffItem diff : diffs) {
            switch (diff.getStatus()) {
                case "added":
                    result.append("  + ").append(diff.getKey()).append(": ")
                            .append(formatValue(diff.getValue())).append("\n");
                    break;
                case "removed":
                    result.append("  - ").append(diff.getKey()).append(": ")
                            .append(formatValue(diff.getValue())).append("\n");
                    break;
                case "unchanged":
                    result.append("    ").append(diff.getKey()).append(": ")
                            .append(formatValue(diff.getValue())).append("\n");
                    break;
                case "changed":
                    result.append("  - ").append(diff.getKey()).append(": ")
                            .append(formatValue(diff.getOldValue())).append("\n");
                    result.append("  + ").append(diff.getKey()).append(": ")
                            .append(formatValue(diff.getNewValue())).append("\n");
                    break;
                default:
                    throw new Exception("Unknown status");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return value.toString();
        }
        return String.valueOf(value);
    }
}
