package parser;

import java.math.BigDecimal;
import java.util.HashMap;

public class JsonObject {
    private final HashMap<String, Object> jsonData;

    public JsonObject(String jsonString) {
        this.jsonData = new JsonParser(jsonString).getJsonObjectDataFromString(jsonString);
    }

    public void put(String key, String stringValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, stringValue);
    }

    public String getString(String key) {
        Object value = getValue(key);

        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Value is not of type String");
        }

        return (String) value;
    }

    public void put(String key, BigDecimal numberValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, numberValue);
    }

    public BigDecimal getInt(String key) {
        Object value = getValue(key);

        if (!(value instanceof BigDecimal)) {
            throw new IllegalArgumentException("Value is not of type Integer");
        }

        return (BigDecimal) value;
    }

    public void put(String key, boolean booleanValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, booleanValue);
    }

    public boolean getBoolean(String key) {
        Object value = getValue(key);

        if (!(value instanceof Boolean)) {
            throw new IllegalArgumentException("Value is not of type Boolean");
        }

        return (boolean) value;
    }

    public void put(String key, JsonObject nestedObject) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, nestedObject);
    }

    public JsonObject getJsonObject(String key) {
        Object value = getValue(key);

        if (!(value instanceof JsonObject)) {
            throw new IllegalArgumentException("Value is not of type JsonObject");
        }

        return (JsonObject) value;
    }

    public void put(String key, JsonArray nestedArray) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, nestedArray);
    }

    public JsonArray getJsonArray(String key) {
        Object value = getValue(key);

        if (!(value instanceof JsonArray)) {
            throw new IllegalArgumentException("Value is not of type JsonArray");
        }

        return (JsonArray) value;
    }

    public void putNull(String key) {
        jsonData.put(key, null);
    }

    private Object getValue(String key) {
        if (!jsonData.containsKey(key)) {
            throw new IllegalArgumentException("Key does not exist");
        }

        Object value = jsonData.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }

        return value;
    }

    @Override
    public String toString() {
        return jsonData.toString();
    }
}
