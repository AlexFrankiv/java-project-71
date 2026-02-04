package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DiffItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<DiffItem> diffs) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        for (DiffItem diff : diffs) {
            Map<String, Object> item = new HashMap<>();
            item.put("key", diff.getKey());
            item.put("status", diff.getStatus());

            switch (diff.getStatus()) {
                case "added":
                case "removed":
                case "unchanged":
                    item.put("value", diff.getValue());
                    break;
                case "changed":
                    item.put("oldValue", diff.getOldValue());
                    item.put("newValue", diff.getNewValue());
                    break;
                default:
                    throw new Exception("Unknown status: " + diff.getStatus());
            }

            resultList.add(item);
        }

        return mapper.writeValueAsString(resultList);
    }
}
