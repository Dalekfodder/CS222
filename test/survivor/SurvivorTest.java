package survivor;

import org.junit.Test;

import static org.junit.Assert.*;

public class SurvivorTest {
    @Test(expected = IllegalArgumentException.class)
    public void testZeroArgumentConstructor0() {
        Survivor world = new Survivor(-1, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroArgumentConstructor1() {
        Survivor world = new Survivor(6, -1);
    }


    @Test
    public void testZeroArgumentConstructor3() {
        Survivor world = new Survivor(4,4);
        assertFalse(world.isLive(0,0));
    }

    @Test
    public void testSetLive() {
        Survivor world = new Survivor(5, 5);
        world.setLive(3,3);
        assertTrue(world.isLive(3, 3));
    }

    @Test
    public void testSetDead() {
        Survivor world = new Survivor(5, 5);
        world.setLive(3,3);
        world.setDead(3,3);
        assertFalse(world.isLive(3, 3));
    }

    @Test
    public void testIsLive1() {
        Survivor world = new Survivor(5,5);
        assertFalse(world.isLive(6, 1));
    }

    @Test
    public void testIsLive2() {
        Survivor world = new Survivor(5,5);
        assertFalse(world.isLive(1,6));
    }

    @Test
    public void testIsLive3() {
        Survivor world = new Survivor(5,5 );
        world.setLive(0,1);
        assertTrue(world.isLive(0,1));
    }

    @Test
    public void testIsLive4() {
        Survivor world = new Survivor(5,5);
        world.setLive(6,6);
        assertFalse(world.isLive(6,6));
    }

    @Test
    public void testPassADay0LiveNeighbours() {
        Survivor world = new Survivor(4, 4);
        world.setLive(3,3);
        world.passADay();
        assertFalse(world.isLive(3,3));
    }

    @Test
    public void testPassADay1LiveNeighbours() {
        Survivor world = new Survivor(4,4);
        world.setLive(3,3);
        world.setLive(3, 2);
        world.passADay();
        assertFalse(world.isLive(3,3));
        assertFalse(world.isLive(3,2));
    }

    @Test
    public void testPassADay2LiveNeighbours() {
        Survivor world = new Survivor(4,4);
        world.setLive(1,2);
        world.setLive(1,1);
        world.setLive(0,2);
        world.passADay();
        assertTrue(world.isLive(1,2));
        assertTrue(world.isLive(1,1));
        assertTrue(world.isLive(0,2));
    }
    @Test
    public void testPassADay3LiveNeighbours() {
        Survivor world = new Survivor(4,4);
        world.setLive(1,2);
        world.setLive(1,1);
        world.setLive(0,2);
        world.setLive(2,2);
        world.passADay();
        assertTrue(world.isLive(1,2));
    }

    @Test
    public void testPassADay4LiveNeighbours() {
        Survivor world = new Survivor(4,4);
        world.setLive(1,3);
        world.setLive(1,2);
        world.setLive(1,1);
        world.setLive(0,2);
        world.setLive(2,2);
        world.passADay();
        assertFalse(world.isLive(1,2));
    }

    @Test
    public void testPassADay5LiveNeighbours() {
        Survivor world = new Survivor(4, 4);
        world.setLive(0,1);
        world.setLive(0,2);
        world.setLive(0,3);
        world.setLive(1,2);
        world.setLive(1,3);
        world.setLive(2,3);
        world.passADay();
        assertFalse(world.isLive(1,2));
    }

    @Test
    public void testPassADayDeadCellWith3Neighbours() {
        Survivor world = new Survivor(4,4);
        world.setLive(0,0);
        world.setLive(0,1);
        world.setLive(1,0);
        world.passADay();
        assertTrue(world.isLive(1,1));
    }

    @Test
    public void testPassADay() {
        Survivor world = new Survivor(4,4);
        world.setLive(1,2);
        world.setLive(2,2);
        world.setLive(3,2);
        world.passADay();
        assertFalse(world.isLive(1,2));
        assertTrue(world.isLive(2,2));
        assertFalse(world.isLive(0,2));
        assertTrue(world.isLive(2,1));
        assertTrue(world.isLive(2,3));
    }

    @Test
    public void testPassADay2() {
        Survivor world = new Survivor(4,4);
        world.setLive(1,2);
        world.setLive(1,1);
        world.setLive(0,3);
        world.setLive(2,3);
        world.passADay();
        assertTrue(world.isLive(1,2));
        assertFalse(world.isLive(1,1));
        assertFalse(world.isLive(0,3));
        assertFalse(world.isLive(2,3));
        assertTrue(world.isLive(0,2));
        assertTrue(world.isLive(2,2));
        assertTrue(world.isLive(1,3));
    }

    @Test
    public void testPassADay3() {
        Survivor world = new Survivor(4, 4);
        world.setLive(3,4);
        world.setLive(3,3);
        world.setLive(2,3);
        world.passADay();
        assertFalse(world.isLive(2,3));
        assertFalse(world.isLive(3,3));
    }
}

