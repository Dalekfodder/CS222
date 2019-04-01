package battleship;

public class Destroyer extends Ship {

    public Destroyer() {
        setSize(4,2);
    }

    @Override
    public boolean isHit(int x, int y) {
        return (y == getYCoordinate() && (x == getXCoordinate() + 1 || x == getXCoordinate() + 2)
                || (y == getYCoordinate() + 1 && x >= getXCoordinate() && x < getXCoordinate() + getWidth()));
    }
}
