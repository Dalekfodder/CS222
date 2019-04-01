package connect4;

import javax.swing.*;

public class BoardController {

    private Board board;
    private BoardPanelView view;

    public BoardController(Board board) {
        this.board = board;
        view = new BoardPanelView(this);
    }

    public void startGame() {
        view.setSize(500,500);
        view.setVisible(true);
        view.updateTitle(board.getCurrentPlayer());
    }

    public void clickedColumn(int column) {
        board.insertChipAt(column + 1);
        view.updateColumn(column, board.getCurrentPlayer());
        view.updateTitle(board.getCurrentPlayer());
        checkWinner();
    }

    private void checkWinner() {
        Chip winner = board.getWinner();
        if(winner != Chip.NONE) {
            int dialogResult = JOptionPane.showConfirmDialog(view, winner
                    + " has won! Do you wish to restart?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (dialogResult == 0) {
                this.board = new Board();
                view.dispose();
                view = new BoardPanelView(this);
                startGame();
            }
            else
                System.exit(0);
        }
    }
}
