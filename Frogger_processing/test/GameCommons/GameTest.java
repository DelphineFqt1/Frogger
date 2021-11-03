package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void car_range() {
        Game game = new Game(45,16,19);
        ArrayList<Obstacle.Car> cars1 = new ArrayList<Obstacle.Car>();
        cars1.add(new Obstacle.Car(1, 1, 1, 45, 16, 1, 1, 1));
        // On vérifie le type
        assertEquals(cars1.getClass(), game.car_range(2).getClass());

    }

    @Test
    void trunk_range() {
        Game game = new Game(45,16,19);
        ArrayList<Obstacle.Trunk> trunks1 = new ArrayList<Obstacle.Trunk>();
        trunks1.add(new Obstacle.Trunk(1, 1, 1, 45, 16, 1, 1, 1));
        // On vérifie le type
        assertEquals(trunks1.getClass(), game.trunk_range(2).getClass());
    }

    @Test
    void allCars() {
        Game game = new Game(45,16,19);
        // On créé une liste de listes de voitures
        ArrayList<ArrayList<Obstacle.Car>> list_cars = new ArrayList<ArrayList<Obstacle.Car>>();
        // On vérifie que allCars renvoie un objet du bon type
        assertEquals(game.allCars(5).getClass(), list_cars.getClass());
        // On vérifie que la liste contient des voitures
        Obstacle.Car car = new Obstacle.Car(1,2,3,4,5,6,7,8);
        assertEquals(game.allCars(6).get(0).get(0).getClass(), car.getClass());

    }

    @Test
    void allTrunks() {
        Game game = new Game(45,16,19);
        // On créé une liste de listes de voitures
        ArrayList<ArrayList<Obstacle.Car>> list_trunks = new ArrayList<ArrayList<Obstacle.Car>>();
        // On vérifie que allCars renvoie un objet du bon type
        assertEquals(game.allTrunks(6,8).getClass(), list_trunks.getClass());
        // On vérifie que la liste contient des troncs
        Obstacle.Trunk trunk = new Obstacle.Trunk(1,2,3,4,5,6,7,8);
        assertEquals(game.allTrunks(6, 8).get(0).get(0).getClass(), trunk.getClass());
    }

    @Test
    void move_allCars() {
        Game game = new Game(45,16,19);
        ArrayList<ArrayList<Obstacle.Car>> cars = game.allCars(1);
        System.out.println(cars.get(0).get(0).getBottom());
        // On vérifie que toutes les voitures de la liste bougent
        game.move_allCars(cars, 1);
        for (ArrayList<Obstacle.Car> list_car : cars) {
            for (Obstacle.Car car : list_car) {
                assertEquals(676, car.getTop());
                assertEquals(631, car.getBottom());
            }
        }

    }

    @Test
    void setFrog() {
        Game game = new Game(45,16,19);
        Frog frog = new Frog(19*45 / 4, 16*45 - 45, 45);
        // On vérifie que setFrog créé un objet de classe Frog
        assertEquals(frog.getClass(), game.setFrog(1).getClass());
        assertEquals(frog.getClass(), game.setFrog(2).getClass());
        // On vérifie que l'objet créé a les bons paramètres
        assertEquals(frog.getLeft(), game.setFrog(1).getLeft());
        assertEquals(frog.getRight(), game.setFrog(1).getRight());
    }

    @Test
    void reset_Frog() {
        Game game = new Game(45,16,19);
        Frog frog = new Frog(19*45 / 4, 16*45 - 45, 45);
        assertEquals(0,frog.getRange());
        // On vérifie que frog a bien changé de rangée
        frog.move(1,-1);
        assertEquals(1,frog.getRange());

    }

    @Test
    void stateFrog() {
        Game game = new Game(45,16,19);
        Frog frog = new Frog(19*45 / 4, 16*45 - 45, 45);
        // On vérifie que GAMEOVER est false au début de la partie
        assertFalse(frog.isGAMEOVER());
        // On vérifie que si frog atteint l'arrivée GameState est true
        frog.setRange(100);
        game.stateFrog(frog, 1);
        assertTrue(game.getGameState());
    }


}