package MovingElements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObstacleTest {

    @Test
    void getAbs_limit() {
        Obstacle.Car obstacle = new Obstacle.Car(1,1,1,1,1,1,1,1);
        assertEquals(1, obstacle.getAbs_limit());
    }

    @Test
    void getOrd_lim() {
        Obstacle.Car obstacle = new Obstacle.Car(1,1,1,1,1,1,1,1);
        assertEquals(1, obstacle.getOrd_lim());
    }

    @Test
    void move() {
        Obstacle.Car car = new Obstacle.Car(1,1,1,1,1,1,1,1);
        car.move(1,1);
        assertEquals(-1,car.getLeft());
        assertEquals(0,car.getRight());
        assertEquals(1,car.getTop());
        assertEquals(0,car.getBottom());
    }
}