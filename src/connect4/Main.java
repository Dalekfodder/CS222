package connect4;

public class Main {
    public static void main(String[] args) {
        Board grid = new Board();
        BoardController controller = new BoardController(grid);
        controller.startGame();
    }
}
