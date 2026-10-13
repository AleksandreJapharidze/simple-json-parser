package parser;

import java.util.ArrayList;

public class JsonArray {
    private final ArrayList<Object> elements;

    public JsonArray(String jsonString) {
        this.elements = JsonParser.getInstance().getJsonArrayContentsFromString(jsonString);
    }

    JsonArray() {
        this.elements = new ArrayList<>();
    }

    ArrayList<Object> getElements() {
        return elements;
    }

    public void add(JsonObject objectValue) {
        elements.add(objectValue);
    }

    public void add(String stringValue) {
        elements.add(stringValue);
    }

    public void add(int intValue) {
        elements.add(intValue);
    }

    public void add(boolean booleanValue) {
        elements.add(booleanValue);
    }

    public void add(JsonArray arrayValue) {
        elements.add(arrayValue);
    }

    public void addNull() {
        elements.add(null);
    }

    public Object get(int index) {
        return elements.get(index);
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
