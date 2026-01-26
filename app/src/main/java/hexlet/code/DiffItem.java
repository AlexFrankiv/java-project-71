package hexlet.code;

public class DiffItem {
    private final String key;
    private final String status;
    private final Object value;
    private final Object oldValue;
    private final Object newValue;

    public DiffItem(String key, String status, Object value) {
        this.key = key;
        this.status = status;
        this.value = value;
        this.oldValue = null;
        this.newValue = null;
    }

    public DiffItem(String key, String status, Object oldValue, Object newValue) {
        this.key = key;
        this.status = status;
        this.value = null;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getKey() {
        return key;
    }
    public String getStatus() {
        return status;
    }
    public Object getValue() {
        return value;
    }
    public Object getOldValue() {
        return oldValue;
    }
    public Object getNewValue() {
        return newValue;
    }
}
