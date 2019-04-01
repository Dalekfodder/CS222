package battleship;

import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class BattleFieldTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor1() {
        BattleField field = new BattleField(-1,30);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor2() {
        BattleField field = new BattleField(30,-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor3() {
        BattleField field = new BattleField(-1,-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut1() {
        BattleField field = new BattleField(10,10);
        Ship boat = new Boat();
        field.put(9, 8, boat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut2() {
        BattleField field = new BattleField(10,10);
        Ship destroyer = new Boat();
        field.put(1, 9, destroyer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut3() {
        BattleField field = new BattleField(10,10);
        Ship boat = new Boat();
        field.put(4, -1, boat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPut4() {
        BattleField field = new BattleField(10,10);
        Ship destroyer = new Boat();
        field.put(-1, 4, destroyer);
    }

    @Test
    public void testPut5() {
        BattleField field = new BattleField(10,10);
        Ship boat = new Boat();
        field.put(4, 8, boat);
        assertEquals(4, boat.getXCoordinate());
        assertEquals(8, boat.getYCoordinate());
    }

    @Test
    public void testShootOutOfBounds() {
        BattleField field = new BattleField(10, 10);
        field.shoot(11,11);
    }

    @Test
    public void testShoot2Hit() {
        BattleField field = new BattleField(10, 10);
        Ship boat = new Boat();
        Ship submarine = new Submarine();
        field.put(4, 8, boat);
        field.put(5, 1, submarine);
        HashSet<Ship> testHits = new HashSet<>();
        field.shoot(4,8);
        field.shoot(5,2);
        assertEquals(testHits, field.getReport());
    }

    @Test
    public void testShoot2Hit1Miss() {
        BattleField field = new BattleField(10, 10);
        Ship boat = new Boat();
        Ship submarine = new Submarine();
        field.put(4, 8, boat);
        field.put(5, 1, submarine);
        HashSet<Ship> testHits = new HashSet<>();
        testHits.add(boat);
        testHits.add(submarine);
        field.shoot(4,8);
        field.shoot(5,2);
        field.shoot(9,1);
        assertEquals(testHits, field.getReport());
    }

    @Test
    public void testShoot3Hit() {
        BattleField field = new BattleField(10, 10);
        Ship boat = new Boat();
        Ship submarine = new Submarine();
        Ship destroyer = new Destroyer();
        field.put(4, 8, boat);
        field.put(5, 1, submarine);
        field.put(1, 4, destroyer);
        HashSet<Ship> testHits = new HashSet<>();
        testHits.add(submarine);
        testHits.add(boat);
        testHits.add(destroyer);
        field.shoot(4,8);
        field.shoot(5,2);
        field.shoot(2,5);
        assertEquals(testHits, field.getReport());
    }

    @Test
    public void testShoot1Hit2Miss() {
        BattleField field = new BattleField(10, 10);
        Ship boat = new Boat();
        field.put(4, 8, boat);
        HashSet<Ship> testHits = new HashSet<>();
        testHits.add(boat);
        field.shoot(4,8);
        field.shoot(9,9);
        field.shoot(9,9);
        assertEquals(testHits, field.getReport());
    }

    @Test
    public void testShoot3Hit1Miss() {
        BattleField field = new BattleField(10, 10);
        Ship boat = new Boat();
        Ship submarine = new Submarine();
        Ship destroyer = new Destroyer();
        field.put(4, 8, boat);
        field.put(5, 1, submarine);
        field.put(1, 4, destroyer);
        HashSet<Ship> testHits = new HashSet<>();
        testHits.add(submarine);
        testHits.add(destroyer);
        field.shoot(4,8);
        field.shoot(5,2);
        field.shoot(2,5);
        field.shoot(9,9);
        assertEquals(testHits, field.getReport());
    }

    @Test
    public void testShoot3Miss1Hit() {
        BattleField field = new BattleField(10, 10);
        Ship boat = new Boat();
        Ship submarine = new Submarine();
        Ship destroyer = new Destroyer();
        field.put(4, 8, boat);
        field.put(5, 1, submarine);
        field.put(1, 4, destroyer);
        HashSet<Ship> testHits = new HashSet<>();
        field.shoot(4,8);
        field.shoot(9,9);
        field.shoot(9,9);
        field.shoot(9,9);
        assertEquals(testHits, field.getReport());
    }

    private BattleField sampleField() {
        // The sample battle field given in battlefield.png
        BattleField field = new BattleField(10, 10);
        Ship boat = new Boat();
        Ship submarine1 = new Submarine();
        Ship destroyer = new Destroyer();
        Ship submarine2 = new Submarine();

        field.put(4, 8, boat);
        field.put(5, 1, submarine1);
        field.put(1, 4, destroyer);
        field.put(6, 5, submarine2);

        return field;
    }
}
