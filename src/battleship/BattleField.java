package battleship;

import java.util.Set;
import java.util.HashSet;

public class BattleField {
    private int width;
    private int height;
    private HashSet<Ship> hits;
    private HashSet<Ship> ships;

    public BattleField(int width, int height) {
        checkGreaterThanZero(width, height);
        this.height = height-1;
        this.width = width-1;
        hits = new HashSet<>();
        ships = new HashSet<>();
    }

    public void put(int x, int y, Ship ship) {
        checkGreaterThanZero(x, y);
        if (ship.getWidth() + x > this.width || ship.getHeight() + y > this.height)
            throw new IllegalArgumentException();
        ship.setLocation(x,y);
        ships.add(ship);
    }

    private void checkGreaterThanZero(int x, int y) {
        if (x < 0 || y < 0)
            throw new IllegalArgumentException();
    }

    public void shoot(int x, int y) {
        if (hits.size() > 2)
            hits.clear();

        for (Ship ship : ships) {
            if(ship.isHit(x, y)) {
                hits.add(ship);
                return;
            }
        }

        hits.add(null);
    }

    public Set<Ship> getReport() {
        System.out.println(hits);
        if (hits.size() < 3) {
            return new HashSet<>();
        }

        while (hits.contains(null))
            hits.remove(null);

        return hits;
    }
}
