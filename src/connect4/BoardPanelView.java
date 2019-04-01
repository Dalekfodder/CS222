package connect4;

import javax.swing.*;
import java.awt.*;

public class BoardPanelView extends BoardView{
    private boolean[][] board;
    private Color color;

    public BoardPanelView(BoardController controller) {
        super(controller);
        board = new boolean[NUM_COLUMNS][NUM_ROWS];
        color = Color.RED;
    }

    @Override
    public void updateColumn(int column, Chip player) {
        for (int i = 0; i < NUM_ROWS; i++) {
            if (!board[column][i]) {
                board[column][i] = true;
                components[column][i].setBackground(color);
                changeColor(player);
                break;
            }
        }
    }

    private void changeColor(Chip player) {
        color = Color.RED;
        if (player == Chip.YELLOW)
            color = Color.YELLOW;
    }

    @Override
    public JComponent getNewButton() {
        return new JButton();
    }
}
