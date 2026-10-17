package parser;

class JsonValidator {
    private JsonValidator() {}

    static boolean isValidJsonNumber(String numberString) {
        if (numberString == null || numberString.isEmpty()) {
            return false;
        }

        int position = 0;
        int length = numberString.length();

        if (numberString.charAt(position) == '-') {
            position++;
            if (position == length) {
                return false;
            }
        }

        if (numberString.charAt(position) == '0') {
            position++;
            if (position < length && isAsciiDigit(numberString.charAt(position))) {
                return false;
            }
        } else if (numberString.charAt(position) >= '1' && numberString.charAt(position) <= '9') {
            position++;

            while (position < length && isAsciiDigit(numberString.charAt(position))) {
                position++;
            }
        } else {
            return false;
        }

        if (position < length && numberString.charAt(position) == '.') {
            position++;
            if (position == length || !isAsciiDigit(numberString.charAt(position))) {
                return false;
            }
        }

        while (position < length && isAsciiDigit(numberString.charAt(position))) {
            position++;
        }

        if (position < length && (numberString.charAt(position) == 'e' || numberString.charAt(position) == 'E')) {
            position++;
            if (position < length && (numberString.charAt(position) == '+' || numberString.charAt(position) == '-')) {
                position++;
            }

            if (position == length || !isAsciiDigit(numberString.charAt(position))) {
                return false;
            }
        }

        while (position < length && isAsciiDigit(numberString.charAt(position))) {
            position++;
        }

        return position == length;
    }

    private static boolean isAsciiDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
