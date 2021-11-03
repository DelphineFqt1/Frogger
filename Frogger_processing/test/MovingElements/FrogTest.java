package MovingElements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrogTest {

    @Test
    void intersect() {
        Obstacle.Car car = new Obstacle.Car(1,1,1,1,1,1,1,1);
        Obstacle.Trunk trunk = new Obstacle.Trunk(2,2,1,1,1,1,1,1);
        Frog frog = new Frog(1,1,1);
        assertTrue(frog.intersect(car));
        assertFalse(frog.intersect(trunk));
    }

    @Test
    void move() {
        Frog frog = new Frog(1,1,1);
        frog.move(1,1);
        assertEquals(2,frog.getLeft());
        assertEquals(3,frog.getRight());
        assertEquals(3,frog.getTop());
        assertEquals(2,frog.getBottom());
    }

    @Test
    void isGAMEOVER() {
        Frog frog = new Frog(1,1,1);
        assertFalse(frog.isGAMEOVER());
    }

    @Test
    void setGAMEOVER() {
        Frog frog = new Frog(1,1,1);
        frog.setGAMEOVER(true);
        assertTrue(frog.isGAMEOVER());
    }

    @Test
    void isCar_intersection() {
        Frog frog = new Frog(1,1,1);
        assertFalse(frog.isCar_intersection());
    }

    @Test
    void setCar_intersection() {
        Frog frog = new Frog(1,1,1);
        frog.setCar_intersection(true);
        assertTrue(frog.isCar_intersection());
    }

    @Test
    void isTrunk_intersection() {
        Frog frog = new Frog(1,1,1);
        assertTrue(frog.isTrunk_intersection());
    }

    @Test
    void setTrunk_intersection() {
        Frog frog = new Frog(1,1,1);
        frog.setTrunk_intersection(false);
        assertFalse(frog.isTrunk_intersection());
    }
}