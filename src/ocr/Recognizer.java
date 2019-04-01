package ocr;

import java.util.HashMap;

public class Recognizer {
    private static int DIGIT_LENGHT = 3;
    private HashMap<String, Integer> allDigits;
    private HashMap<String, Character> allChars;

    public String convert(String input) {
        String convertedString = "";
        String[] lines = input.split("\n");
        int amountOfLines = lines.length;

        for (int i = 0; i < amountOfLines; i += 4) {
            if (i > 1) convertedString += ",";
            int amountOfDigits = lines[i].length() / DIGIT_LENGHT;

            for (int j = 0; j < amountOfDigits; j++) {
                String temp = String.join("\n",
                        lines[i].substring(j * DIGIT_LENGHT, j * DIGIT_LENGHT + DIGIT_LENGHT),
                        lines[i+1].substring(j * DIGIT_LENGHT, j * DIGIT_LENGHT + DIGIT_LENGHT),
                        lines[i+2].substring(j * DIGIT_LENGHT, j * DIGIT_LENGHT + DIGIT_LENGHT),
                        lines[i+3].substring(j * DIGIT_LENGHT, j * DIGIT_LENGHT + DIGIT_LENGHT)
                );

                convertedString += recognize(temp);
            }
        }

        if (convertedString.length() <= 0)
            convertedString += "?";

        return convertedString;
    }

    private String recognize(String input) {
        populateAllDigits();
        populateAllCharacters();

        if (allDigits.get(input) != null)
            return allDigits.get(input).toString();
        else if (allChars.get(input) != null)
            return allChars.get(input).toString();
        else
            return "?";

    }

    private void populateAllDigits() {
        allDigits = new HashMap<>();

        allDigits.put(String.join("\n",
                " _ ",
                "| |",
                "|_|",
                "   "), 0);

        allDigits.put(String.join("\n",
                "   ",
                "  |",
                "  |",
                "   "), 1);

        allDigits.put(String.join("\n",
                " _ ",
                " _|",
                "|_ ",
                "   "), 2);

        allDigits.put(String.join("\n",
                " _ ",
                " _|",
                " _|",
                "   "), 3);

        allDigits.put(String.join("\n",
                "   ",
                "|_|",
                "  |",
                "   "), 4);
        allDigits.put(String.join("\n",
                " _ ",
                "|_ ",
                " _|",
                "   "), 5);
        allDigits.put(String.join("\n",
                " _ ",
                "|_ ",
                "|_|",
                "   "), 6);
        allDigits.put(String.join("\n",
                " _ ",
                "  |",
                "  |",
                "   "), 7);
        allDigits.put(String.join("\n",
                " _ ",
                "|_|",
                "|_|",
                "   "), 8);
        allDigits.put(String.join("\n",
                " _ ",
                "|_|",
                " _|",
                "   "), 9);
    }

    private void populateAllCharacters() {
        allChars = new HashMap<>();
        allChars.put(String.join("\n",
                " _ ",
                "|_|",
                "| |",
                "   "), 'A');
        allChars.put(String.join("\n",
                "   ",
                "|_ ",
                "|_|",
                "   "), 'B');
        allChars.put(String.join("\n",
                " _ ",
                "|  ",
                "|_ ",
                "   "), 'C');
        allChars.put(String.join("\n",
                "   ",
                " _|",
                "|_|",
                "   "), 'D');
        allChars.put(String.join("\n",
                " _ ",
                "|_ ",
                "|_ ",
                "   "), 'E');
        allChars.put(String.join("\n",
                " _ ",
                "|_ ",
                "|  ",
                "   "), 'F');
    }
}
