package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String content1 = readFile(filePath1);
        String content2 = readFile(filePath2);

        String dataFormat = getDataFormat(filePath1); // Предполагаем одинаковый формат у обоих файлов

        Map<String, Object> data1 = Parser.parse(content1, dataFormat);
        Map<String, Object> data2 = Parser.parse(content2, dataFormat);

        List<DiffItem> diffs = DiffBuilder.build(data1, data2);

        return Formatter.format(diffs, formatName);
    }

    private static String readFile(String filePath) throws Exception {
        return Files.readString(Paths.get(filePath));
    }

    private static String getDataFormat(String filePath) throws Exception {
        int getLastIndex = filePath.lastIndexOf('.');
        return filePath.substring(getLastIndex + 1).toLowerCase();
    }
}
