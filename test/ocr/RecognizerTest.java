package ocr;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecognizerTest {
    @Test
    public void testDigit0() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "| |",
                "|_|",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("0", output);
    }

    @Test
    public void test123() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "    _  _ ",
                "  | _| _|",
                "  ||_  _|",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("123", output);
    }

    @Test
    public void test1_3() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "    _  _ ",
                "  ||_| _|",
                "  ||_  _|",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("1?3", output);
    }
    @Test
    public void test12a() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "    _  _ ",
                "  | _||_|",
                "  ||_ | |",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("12A", output);
    }
    @Test
    public void testa() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_|",
                "| |",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("A", output);
    }

    @Test
    public void testad() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _    ",
                "|_| _|",
                "| ||_|",
                "      ");

        String output = recognizer.convert(input);
        assertEquals("AD", output);
    }

    @Test
    public void testa_1() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _       ",
                "|_| |   |",
                "| |     |",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("A?1", output);
    }

    @Test
    public void testa_b() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _       ",
                "|_| | |_ ",
                "| |   |_|",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("A?B", output);
    }

    @Test
    public void testf1d() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _       ",
                "|_   | _|",
                "|    ||_|",
                "         ");

        String output = recognizer.convert(input);
        assertEquals("F1D", output);
    }

    @Test
    public void testMultipleLines() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_|",
                "| |",
                "   ",
                " _ ",
                "|_|",
                "| |",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("A,A", output);
    }

    @Test
    public void testMultipleLines2() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                " _ ",
                "|_|",
                "| |",
                "   ",
                "   ",
                "| |",
                "| |",
                "   ");

        String output = recognizer.convert(input);
        assertEquals("A,?", output);
    }

    @Test
    public void test_() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n", " ");
        String output = recognizer.convert(input);
        assertEquals("?", output);
    }

    @Test
    public void test() {
        Recognizer recognizer = new Recognizer();
        String input = String.join("\n",
                "   ",
                "a  |",
                "  |",
                "   ");
        String output = recognizer.convert(input);
        assertEquals("?", output);
    }
}
