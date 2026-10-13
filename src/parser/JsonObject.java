package parser;

import java.util.HashMap;

public class JsonObject {
    private final HashMap<String, Object> jsonData;

    public JsonObject(String jsonString) {
        this.jsonData = new HashMap<>();

        JsonParser parser = new JsonParser(jsonString);
        parser.getObjectFromString(this);
    }

    JsonObject() {
        this.jsonData = new HashMap<>();
    }

    public void put(String key, String stringValue) {
        jsonData.put(key, stringValue);
    }

    public String getString(String key) {
        return (String) jsonData.get(key);
    }

    public void put(String key, int integerValue) {
        jsonData.put(key, integerValue);
    }

    public Integer getInt(String key) {
        return (Integer) jsonData.get(key);
    }

    public void putNull(String key) {
        jsonData.put(key, null);
    }

    public void put(String key, boolean booleanValue) {
        jsonData.put(key, booleanValue);
    }

    public boolean getBoolean(String key) {
        return (boolean) jsonData.get(key);
    }

    public void put(String key, JsonObject nestedObject) {
        jsonData.put(key, nestedObject);
    }

    public JsonObject getJsonObject(String key) {
        return (JsonObject) jsonData.get(key);
    }

    public void put(String key, JsonArray nestedArray) {
        jsonData.put(key, nestedArray);
    }

    @Override
    public String toString() {
        return jsonData.toString();
    }
}
