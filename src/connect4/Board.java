package connect4;

enum Chip {
    NONE, RED, YELLOW;

    @Override
    public String toString() {
        if (this == RED)
            return "R";
        else if (this == YELLOW)
            return "Y";
        else
            return "_";
    }
}

public class Board {
    public static final int NUM_COLUMNS = 7;
    public static final int NUM_ROWS = 6;
    private Chip[][] board;
    private Chip currentPlayer;
    private String configuration;

    /**
     * Creates an empty Connect4 board.
     * The first player to play will be the RED one.
     */
    public Board() {
        board = new Chip[NUM_COLUMNS][NUM_ROWS];
        configuration = "";
        currentPlayer = Chip.RED;
        setEmptyBoard();

    }

    private void setEmptyBoard(){
        for (int i = 0; i < NUM_COLUMNS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                board[i][j] = Chip.NONE;
            }
        }
    }

    /**
         * Creates a Connect4 board with the given initial configuration.
     * For the format of the configuration, see
     * http://blog.gamesolver.org/solving-connect-four/02-test-protocol/
     *
     * @param configuration The initial configuration of the board.
     * @throws IllegalArgumentException if <code>configuration</code> is not a valid configuration.
     *                                  A configuration is not valid if it is an impossible combination
     *                                  or it contains moves after forming a 4-connection.
     */
    public Board(String configuration) {
        this();
        populateBoard(configuration);
    }

    private void populateBoard(String configuration){
        for (int i = 0; i < configuration.length(); i++) {
            int columnInt = Character.getNumericValue(configuration.charAt(i));

            insertChipAt(columnInt);
        }
    }

    private void changeCurrentPlayer(){
        if (currentPlayer == Chip.RED) {
            currentPlayer = Chip.YELLOW;
        } else {
            currentPlayer = Chip.RED;
        }
    }

    /**
     * Inserts a chip for the current player at the specified <code>column</code>
     * (indexing starts at 1). After the chip is inserted, it wil be the other player's turn.
     *
     * @param column The index of the column into which to put a chip.
     * @throws IllegalArgumentException if no column at the specified index exists.
     * @throws RuntimeException         if inserting a chip at the specified column is not possible (e.g. when the column is full)
     */
    public void insertChipAt(int column) {
        column -= 1;
        if(column >= NUM_COLUMNS || column < 0 || getWinner() != Chip.NONE)
            throw new IllegalArgumentException();

        for (int i = 0; i < NUM_ROWS; i++) {
            if (board[column][i] == Chip.NONE) {
                board[column][i] = currentPlayer;
                configuration += column + 1;
                changeCurrentPlayer();
                return;
            }
        }

        throw new RuntimeException();
    }

    public Chip getChipAt(int row, int column) { return board[column][row]; }

    /**
     * @return the winner Chip (RED or YELLOW) if there exists a 4-connection;
     * NONE, otherwise.
     */
    public Chip getWinner() {
        if (checkRowWin() != Chip.NONE) return checkRowWin();
        else if (checkColumnWin() != Chip.NONE) return checkColumnWin();
        else if (checkRightDiagonalWin() != Chip.NONE) return checkRightDiagonalWin();
        else if (checkLeftDiagonalWin() != Chip.NONE) return checkLeftDiagonalWin();
        return Chip.NONE;
    }

    private Chip checkRowWin() {
        for (int i = 0; i < NUM_COLUMNS - 3; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                if (board[i][j] != Chip.NONE
                        && board[i][j] == board[i + 1][j]
                        && board[i][j] == board[i + 2][j]
                        && board[i][j] == board[i + 3][j])
                    return board[i][j];
            }
        }
        return Chip.NONE;
    }

    private Chip checkRightDiagonalWin() {
        for (int i = 0; i < NUM_COLUMNS - 3; i++) {
            for (int j = 0; j < NUM_ROWS - 3; j++) {
                if ( board[i][j] != Chip.NONE
                        && board[i][j] == board[i + 1][j + 1]
                        && board[i][j] == board[i + 2][j + 2]
                        && board[i][j] == board[i + 3][j + 3])
                    return board[i][j];
            }
        }
        return Chip.NONE;
    }

    private Chip checkLeftDiagonalWin() {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            for (int j = NUM_ROWS - 1; j >= 3; j--) {
                if ( board[i][j] != Chip.NONE
                        && board[i][j] == board[i + 1][j - 1]
                        && board[i][j] == board[i + 2][j - 2]
                        && board[i][j] == board[i + 3][j - 3])
                    return board[i][j];
            }
        }
        return Chip.NONE;
    }

    private Chip checkColumnWin() {
        for (int i = 0; i < NUM_COLUMNS; i++) {
            for (int j = 0; j < NUM_ROWS - 3; j++) {
                if (board[i][j] != Chip.NONE
                        && board[i][j] == board[i][j + 1]
                        && board[i][j] == board[i][j + 2]
                        && board[i][j] == board[i][j + 3])
                    return board[i][j];
            }
        }
        return Chip.NONE;
    }


    /**
     * @return The current state of the board as a "configuration".
     */
    public String getConfiguration() {
        return configuration;
    }


    /**
     * @return The Chip of the current player.
     */
    public Chip getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @return this board's string representation.
     * E.g. here is the output for the configuration 3432554:
     * <p>
     * |_ _ _ _ _ _ _|
     * |_ _ _ _ _ _ _|
     * |_ _ _ _ _ _ _|
     * |_ _ _ _ _ _ _|
     * |_ _ R R Y _ _|
     * |_ Y R Y R _ _|
     */
    public String toString() {
        StringBuilder boardAsString = new StringBuilder();

        for (int i = NUM_ROWS - 1; i >= 0; i--) {
            boardAsString.append("|");
            for (int j = 0; j < NUM_COLUMNS; j++) {
                boardAsString.append(board[j][i]).append(" ");
            }
            boardAsString.deleteCharAt(boardAsString.length()-1);
            boardAsString.append("|" + "\n");
        }

        return boardAsString.toString();
    }
}
