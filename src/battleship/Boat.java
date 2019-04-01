package battleship;

public class Boat extends Ship {

    public Boat() {
        setSize(2,1);
    }


    @Override
    public boolean isHit(int x, int y) {
        return (y == this.getYCoordinate() && (x >= this.getXCoordinate() && x < this.getXCoordinate() + this.getWidth()));
    }
}
