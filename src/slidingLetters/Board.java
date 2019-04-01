package slidingLetters;

enum Direction {
    LEFT, RIGHT, UP, DOWN
}

public class Board {
    private final int dimension;
    private final char tiles[][];
    private int emptyY;
    private int emptyX;

    public Board(int dimension) {
        this.dimension = dimension;
        tiles = new char[dimension][dimension];
        populateTiles();
        emptyY = emptyX = dimension-1;
    }

    private void populateTiles(){
        char letter = 'A';
        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                tiles[i][j] = letter;
                letter++;
            }
        }

        tiles[dimension-1][dimension-1] = ' '; // Need to manually set the final place to empty.
    }
    // Returns the letter at row i, column j.
    // The top-left corner is 0, 0.
    public char getTile(int i, int j) {
        return tiles[i][j];
    }

    // A board is "solved" if it is in the initial state.
    // That is, the top-left tile value is 'A',
    // the empty slot is at the bottom-right corner,
    // and all the other tile values increase by one in row-major order.
    public boolean isSolved() {
        Board originalState = new Board(dimension);
        return originalState.toString().equals(this.toString());
    }

    // Shift "amount"-many tiles towards the empty slot.
    // If "amount" is not positive simply quit.
    // If it is larger than the number of tiles that can be shifted,
    // shift as many tiles as possible and stop.
    // Do not throw an exception or an error.
    // Also increments or decrements the emptyY and Y values depending on the swaps. Provides a faster solution.
    public void shift(Direction direction, int amount) {
        if (amount <= 0)
            return;

        // We shift the empty position rather than shifting the letters.

        else if(direction == Direction.LEFT){
            for (int i = 1; i <= amount; i++)
                if (emptyX + 1 < dimension) {
                    swap(emptyY, emptyX, emptyY, emptyX+1);
                    emptyX++;
                }
        }

        else if (direction == Direction.RIGHT){
            for (int i = 1; i <= amount; i++)
                if(emptyX - 1 >= 0){
                    swap(emptyY, emptyX, emptyY, emptyX-1);
                    emptyX--;
                }
        }

        else if(direction == Direction.UP){
            for(int i = 1; i <= amount; i++)
                if(emptyY + 1 < dimension){
                    swap(emptyY, emptyX, emptyY+1, emptyX);
                    emptyY++;
                }
        }

        else if(direction == Direction.DOWN){
            for(int i = 1; i <= amount; i++)
                if(emptyY - 1 >= 0){
                    swap(emptyY, emptyX, emptyY-1, emptyX);
                    emptyY--;
                }
            }
    }

    private void swap(int y, int x, int y1, int x1){
        char temp = tiles[y][x];
        tiles[y][x] = tiles[y1][x1];
        tiles[y1][x1] = temp;
    }

    // toString() is already implemented for you.
    // Do NOT change it.
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                builder.append(getTile(i, j));
                builder.append(" ");
            }
            if (i != dimension - 1)
                builder.append("\n");
        }
        return builder.toString();
    }
}
