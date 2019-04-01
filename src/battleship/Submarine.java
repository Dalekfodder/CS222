package battleship;

public class Submarine extends Ship {
    public Submarine() {
        setSize(3,2);
    }

    @Override
    public boolean isHit(int x, int y) {
        return (y == getYCoordinate() && x == getXCoordinate() + 1)
                || (y == getYCoordinate() + 1 && x >= getXCoordinate() && x < getXCoordinate() + getWidth());
    }
}
