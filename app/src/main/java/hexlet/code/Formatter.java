package hexlet.code;

import hexlet.code.fornatters.Json;
import hexlet.code.fornatters.Plain;
import hexlet.code.fornatters.Stylish;

import java.util.List;

public class Formatter {
    public static String format(List<DiffItem> diffs, String formatName) throws Exception {
        return switch (formatName) {
            case "plain" -> Plain.format(diffs);
            case "json" -> Json.format(diffs);
            case "stylish" -> Stylish.format(diffs);
            default -> Stylish.format(diffs);
        };
    }
}
