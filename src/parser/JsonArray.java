package parser;

import java.util.ArrayList;

public class JsonArray {
    private final ArrayList<Object> elements;

    JsonArray() {
        this.elements = new ArrayList<>();
    }

    public void add(JsonObject objectValue) {
        this.elements.add(objectValue);
    }

    public void add(String stringValue) {
        this.elements.add(stringValue);
    }

    public void add(int intValue) {
        this.elements.add(intValue);
    }

    public void add(boolean booleanValue) {
        this.elements.add(booleanValue);
    }

    public void add(JsonArray arrayValue) {
        this.elements.add(arrayValue);
    }

    public void addNull() {
        this.elements.add(null);
    }

    public Object get(int index) {
        return this.elements.get(index);
    }

    @Override
    public String toString() {
        return this.elements.toString();
    }
}
