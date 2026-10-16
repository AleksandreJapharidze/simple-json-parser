package parser;

import java.math.BigDecimal;
import java.util.ArrayList;

public class JsonArray {
    private final ArrayList<Object> elements;

    public JsonArray(String jsonString) {
        this.elements = new JsonParser(jsonString).getJsonArrayContentsFromString(jsonString);
    }

    public void add(JsonObject objectValue) {
        elements.add(objectValue);
    }

    public JsonObject getJsonObject(int index) {
        Object element = getElement(index);
        if (!(element instanceof JsonObject)) {
            throw new IllegalArgumentException("Element at index " + index + " is not a JsonObject");
        }
        return (JsonObject) element;
    }

    public void add(String stringValue) {
        elements.add(stringValue);
    }

    public String getString(int index) {
        Object element = getElement(index);
        if (!(element instanceof String)) {
            throw new IllegalArgumentException("Element at index " + index + " is not a String");
        }
        return (String) element;
    }

    public void add(BigDecimal numberValue) {
        elements.add(numberValue);
    }

    public BigDecimal getNumber(int index) {
        Object element = getElement(index);
        if (!(element instanceof BigDecimal)) {
            throw new IllegalArgumentException("Element at index " + index + " is not a BigDecimal");
        }
        return (BigDecimal) element;
    }

    public void add(boolean booleanValue) {
        elements.add(booleanValue);
    }

    public boolean getBoolean(int index) {
        Object element = getElement(index);
        if (!(element instanceof Boolean)) {
            throw new IllegalArgumentException("Element at index " + index + " is not a Boolean");
        }
        return (Boolean) element;
    }

    public void add(JsonArray arrayValue) {
        elements.add(arrayValue);
    }

    public JsonArray getJsonArray(int index) {
        Object element = getElement(index);
        if (!(element instanceof JsonArray)) {
            throw new IllegalArgumentException("Element at index " + index + " is not a JsonArray");
        }
        return (JsonArray) element;
    }

    public void addNull() {
        elements.add(null);
    }

    private Object getElement(int index) {
        if (index < 0 || index >= elements.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Object element = elements.get(index);
        if (element == null) {
            throw new IllegalArgumentException("Element at index " + index + " is null");
        }
        return element;
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}
