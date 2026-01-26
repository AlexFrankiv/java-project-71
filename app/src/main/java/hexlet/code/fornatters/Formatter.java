package hexlet.code.fornatters;

import hexlet.code.DiffItem;

import java.util.List;

public class Formatter {
    public static String format(List<DiffItem> diffs, String formatName) throws Exception {
        return switch (formatName) {
            case "plain" -> Plain.format(diffs);
            case "stylish" -> Stylish.format(diffs);
            default -> Stylish.format(diffs);
        };
    }
}
