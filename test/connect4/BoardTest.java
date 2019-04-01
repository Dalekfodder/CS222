package connect4;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void testZeroArgumentConstructor() {
        Board board = new Board();
        assertEquals(Chip.RED, board.getCurrentPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneArgumentConstructor1() {
        Board board = new Board("0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneArgumentConstructor2() {
        Board board = new Board("8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneArgumentConstructor3() {
        Board board = new Board("121212126");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneArgumentConstructor4() {
        Board board = new Board("172737575");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneArgumentConstructor5() {
        Board board = new Board("0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneArgumentConstructor6() {
        Board board = new Board("1a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneArgumentConstructor7() {
        Board board = new Board("a");
    }


    @Test
    public void testGetConfiguration1() {
        String config = "1111";
        Board board = new Board(config);
        assertEquals(config, board.getConfiguration());
    }

    @Test
    public void testGetConfiguration2() {
        Board board = new Board("");
        board.insertChipAt(1);
        assertEquals("1", board.getConfiguration());
    }

    @Test
    public void testGetCurrentPlayerYellow() {
        Board board = new Board("1");
        assertEquals(Chip.YELLOW, board.getCurrentPlayer());
    }

    @Test
    public void testGetCurrentPlayerRed() {
        Board board = new Board("12");
        assertEquals(Chip.RED, board.getCurrentPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertChipAt1() {
        Board board = new Board("0");
        board.insertChipAt(8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertChipAt2() {
        Board board = new Board();
        board.insertChipAt(0);
    }

    @Test(expected = RuntimeException.class)
    public void  testInsertChipAt3() {
        Board board = new Board("1111111");
        board.insertChipAt(1);
    }

    @Test
    public void testGetWinnerRow() {
        Board board = new Board("1626364");
        assertEquals(Chip.RED, board.getWinner());
    }

    @Test
    public void testGetWinnerColumn() {
        Board board = new Board("16162636");
        assertEquals(Chip.YELLOW, board.getWinner());
    }

    @Test
    public void testGetWinnerDiagonal() {
        Board board = new Board("12233434464");
        assertEquals(Chip.RED, board.getWinner());
    }

    @Test
    public void testToString() {
        Board board = new Board("12");
        String expected = "|_ _ _ _ _ _ _|\n" +
                          "|_ _ _ _ _ _ _|\n" +
                          "|_ _ _ _ _ _ _|\n" +
                          "|_ _ _ _ _ _ _|\n" +
                          "|_ _ _ _ _ _ _|\n" +
                          "|R Y _ _ _ _ _|\n";
        assertEquals(expected, board.toString());

    }
}

