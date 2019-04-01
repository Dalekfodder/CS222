package battleship;

public abstract class Ship {
    // (x,y): The location of the top-left corner of the imaginary
    // box that surrounds the ship.
    private int xCoordinate;
    private int yCoordinate;

    private int width;
    private int height;

    public void setLocation(int x, int y) {
        xCoordinate = x;
        yCoordinate = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract boolean isHit(int x, int y);

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getXCoordinate() { return xCoordinate; }
    public int getYCoordinate() { return yCoordinate; }
}
