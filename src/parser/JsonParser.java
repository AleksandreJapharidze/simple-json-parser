package parser;

import java.util.ArrayList;

class JsonParser {
    private static final JsonParser INSTANCE = new JsonParser();

    static JsonParser getInstance() {
        return INSTANCE;
    }

    private String jsonString;
    private int position;

    private JsonParser() {}

    void parseObjectFromString(String jsonString, JsonObject object) {
        this.jsonString = jsonString;
        this.position = 0;

        skipUnmeaningfulChars();

        if (jsonString.charAt(position) != '{') {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }

        handleKeyParsing(object);
    }

    ArrayList<Object> getJsonArrayContentsFromString(String jsonString) {
        this.jsonString = jsonString;
        this.position = 0;

        skipUnmeaningfulChars();

        if (jsonString.charAt(position) != '[') {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }

        return parseAndReturnArray().getElements();
    }

    private void handleKeyParsing(JsonObject object) {
        position++;
        skipUnmeaningfulChars();

        if (jsonString.charAt(position) == '}') {
            return;
        }

        if (jsonString.charAt(position) != '\"') {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }

        String key = parseAndReturnString();

        position++;
        skipUnmeaningfulChars();
        if (jsonString.charAt(position) != ':') {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }

        position++;
        skipUnmeaningfulChars();
        determineTypeAndMakeNextMove(key, object);
    }

    private void handleObjectParsing(String key, JsonObject object) {
        JsonObject nestedObject = new JsonObject();
        handleKeyParsing(nestedObject);

        object.put(key, nestedObject);
        position++;
        skipUnmeaningfulChars();

        decideNextActionForObjectParsing(object);
    }

    private void handleObjectParsing(JsonArray array) {
        JsonObject object = new JsonObject();
        handleKeyParsing(object);

        array.add(object);
        position++;
        skipUnmeaningfulChars();

        decideNextActionForArrayParsing(array);
    }

    private void handleStringParsing(String key, JsonObject object) {
        object.put(key, parseAndReturnString());

        position++;
        skipUnmeaningfulChars();

        decideNextActionForObjectParsing(object);
    }

    private void handleStringParsing(JsonArray array) {
        array.add(parseAndReturnString());

        position++;
        skipUnmeaningfulChars();

        decideNextActionForArrayParsing(array);
    }

    private void handleNumberParsing(String key, JsonObject object) {
        object.put(key, parseAndReturnNumber());

        skipUnmeaningfulChars();
        decideNextActionForObjectParsing(object);
    }

    private void handleNumberParsing(JsonArray array) {
        array.add(parseAndReturnNumber());

        skipUnmeaningfulChars();
        decideNextActionForArrayParsing(array);
    }

    private void handleBooleanParsing(String key, JsonObject object) {
        object.put(key, parseAndReturnBoolean());

        skipUnmeaningfulChars();
        decideNextActionForObjectParsing(object);
    }

    private void handleBooleanParsing(JsonArray array) {
        array.add(parseAndReturnBoolean());

        skipUnmeaningfulChars();
        decideNextActionForArrayParsing(array);
    }

    private void handleNullParsing(String key, JsonObject object) {
        checkIfNull();
        object.putNull(key);

        skipUnmeaningfulChars();
        decideNextActionForObjectParsing(object);
    }

    private void handleNullParsing(JsonArray array) {
        checkIfNull();
        array.addNull();

        skipUnmeaningfulChars();
        decideNextActionForArrayParsing(array);
    }

    private void handleArrayParsing(String key, JsonObject object) {
        object.put(key, parseAndReturnArray());

        position++;
        skipUnmeaningfulChars();
        decideNextActionForObjectParsing(object);
    }

    private void handleArrayParsing(JsonArray array) {
        array.add(parseAndReturnArray());

        position++;
        skipUnmeaningfulChars();
        decideNextActionForArrayParsing(array);
    }

    private String parseAndReturnString() {
        StringBuilder builder = new StringBuilder();
        position++;
        while (jsonString.charAt(position) != '\"') {
            builder.append(jsonString.charAt(position));
            position++;
        }

        return builder.toString();
    }

    private int parseAndReturnNumber() {
        StringBuilder builder = new StringBuilder();
        if (jsonString.charAt(position) == '-') {
            builder.append(jsonString.charAt(position));
            position++;
        }
        while (Character.isDigit(jsonString.charAt(position))) {
            builder.append(jsonString.charAt(position));
            position++;
        }
        try {
            return Integer.parseInt(builder.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }
    }

    private boolean parseAndReturnBoolean() {
        String string = readFollowingLetters();

        if ("true".equals(string)) {
            return true;
        } else if ("false".equals(string)) {
            return false;
        } else {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }
    }

    private void checkIfNull() {
        String string = readFollowingLetters();

        if (!"null".equals(string)) {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }
    }

    private JsonArray parseAndReturnArray() {
        JsonArray array = new JsonArray();

        position++;
        skipUnmeaningfulChars();
        if (jsonString.charAt(position) == ']') {
            return array;
        }

        determineTypeAndMakeNextMove(array);

        return array;
    }

    private void decideNextActionForObjectParsing(JsonObject object) {
        if (jsonString.charAt(position) == ',') {
            handleKeyParsing(object);
        } else if (jsonString.charAt(position) == '}') {
            return;
        } else {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }
    }

    private void decideNextActionForArrayParsing(JsonArray array) {
        if (jsonString.charAt(position) == ',') {
            position++;
            skipUnmeaningfulChars();
            determineTypeAndMakeNextMove(array);
        } else if (jsonString.charAt(position) == ']') {
            return;
        } else {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }
    }

    private void determineTypeAndMakeNextMove(String key, JsonObject object) {
        if (jsonString.charAt(position) == '\"') {
            handleStringParsing(key, object);
        } else if (Character.isDigit(jsonString.charAt(position)) || jsonString.charAt(position) == '-') {
            handleNumberParsing(key, object);
        } else if (jsonString.charAt(position) == 't' || jsonString.charAt(position) == 'f') {
            handleBooleanParsing(key, object);
        } else if (jsonString.charAt(position) == 'n') {
            handleNullParsing(key, object);
        } else if (jsonString.charAt(position) == '{') {
            handleObjectParsing(key, object);
        } else if (jsonString.charAt(position) == '[') {
            handleArrayParsing(key, object);
        } else {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }
    }

    private void determineTypeAndMakeNextMove(JsonArray array) {
        if (jsonString.charAt(position) == '\"') {
            handleStringParsing(array);
        } else if (Character.isDigit(jsonString.charAt(position)) || jsonString.charAt(position) == '-') {
            handleNumberParsing(array);
        } else if (jsonString.charAt(position) == 't' || jsonString.charAt(position) == 'f') {
            handleBooleanParsing(array);
        } else if (jsonString.charAt(position) == 'n') {
            handleNullParsing(array);
        } else if (jsonString.charAt(position) == '{') {
            handleObjectParsing(array);
        } else if (jsonString.charAt(position) == '[') {
            handleArrayParsing(array);
        } else {
            throw new IllegalArgumentException("JSON string doesn't represent a valid JSON");
        }
    }

    private String readFollowingLetters() {
        StringBuilder builder = new StringBuilder();
        while ((jsonString.charAt(position) >= 'A' && jsonString.charAt(position) <= 'Z') ||
                (jsonString.charAt(position) >= 'a' && jsonString.charAt(position) <= 'z')) {
            builder.append(jsonString.charAt(position));
            position++;
        }
        return builder.toString();
    }

    private void skipUnmeaningfulChars() {
        while (jsonString.charAt(position) == ' ' ||
                jsonString.charAt(position) == '\t' ||
                jsonString.charAt(position) == '\n' ||
                jsonString.charAt(position) == '\r') {
            position++;
        }
    }
}
