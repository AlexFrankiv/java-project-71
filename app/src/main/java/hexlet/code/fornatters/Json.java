package hexlet.code.fornatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<DiffItem> diffs) throws Exception {
        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();

        for (DiffItem diff : diffs) {
            String status = diff.getStatus();
            result.computeIfAbsent(status, k -> new ArrayList<>());

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("key", diff.getKey());

            if ("changed".equals(status)) {
                item.put("oldValue", diff.getOldValue());
                item.put("newValue", diff.getNewValue());
            } else {
                item.put("value", diff.getValue());
            }

            result.get(status).add(item);
        }

        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(result);
    }
}
