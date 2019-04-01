package connect4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class BoardView extends JFrame{
    public static final int NUM_COLUMNS = 7;
    public static final int NUM_ROWS = 6;
    protected JComponent[][] components = new JComponent[NUM_COLUMNS][NUM_ROWS];

    public BoardView(final BoardController controller) {
        this.dispose();
        setLayout(new GridLayout(NUM_ROWS, NUM_COLUMNS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = NUM_ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < NUM_COLUMNS; j++) {
                components[j][i] = getNewButton();
                add(components[j][i]);

                final int column = j;

                components[j][i].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent arg0) {
                        controller.clickedColumn(column);
                    }
                });
            }
        }
    }

    public abstract void updateColumn(int column, Chip player);

    public abstract JComponent getNewButton();

    public void updateTitle(Chip player) {
        setTitle(player + "'s turn.");
    }
}
