package parser;

import java.util.HashMap;

public class JsonObject {
    private final HashMap<String, Object> jsonData;

    public JsonObject(String jsonString) {
        this.jsonData = new HashMap<>();

        JsonParser.getInstance().parseObjectFromString(jsonString, this);
    }

    JsonObject() {
        this.jsonData = new HashMap<>();
    }

    public void put(String key, String stringValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, stringValue);
    }

    public String getString(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        Object value = jsonData.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }

        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Value is not of type String");
        }

        return (String) jsonData.get(key);
    }

    public void put(String key, int integerValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, integerValue);
    }

    public Integer getInt(String key) {
        if (!jsonData.containsKey(key)) {
            throw new IllegalArgumentException("Key does not exist");
        }

        Object value = jsonData.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }

        if (!(value instanceof Integer)) {
            throw new IllegalArgumentException("Value is not of type Integer");
        }

        return (Integer) jsonData.get(key);
    }

    public void putNull(String key) {
        jsonData.put(key, null);
    }

    public void put(String key, boolean booleanValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, booleanValue);
    }

    public boolean getBoolean(String key) {
        if (!jsonData.containsKey(key)) {
            throw new IllegalArgumentException("Key does not exist");
        }

        Object value = jsonData.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }

        if (!(value instanceof Boolean)) {
            throw new IllegalArgumentException("Value is not of type Boolean");
        }

        return (boolean) jsonData.get(key);
    }

    public void put(String key, JsonObject nestedObject) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, nestedObject);
    }

    public JsonObject getJsonObject(String key) {
        Object value = jsonData.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Key does not exist or value is null");
        }

        if (!(value instanceof JsonObject)) {
            throw new IllegalArgumentException("Value is not of type JsonObject");
        }

        return (JsonObject) jsonData.get(key);
    }

    public void put(String key, JsonArray nestedArray) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be of type String");
        }

        jsonData.put(key, nestedArray);
    }

    @Override
    public String toString() {
        return jsonData.toString();
    }
}
