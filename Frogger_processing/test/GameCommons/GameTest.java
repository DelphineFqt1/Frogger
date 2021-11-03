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
        // On vérifie que la liste a la bonne taille
        ArrayList<Obstacle.Car> range = game.car_range(2);
        assertTrue(range.size() == 2 || range.size() == 3);
        // On vérifie qu'en fonction de la parité la vitesse est correcte
        ArrayList<Obstacle.Car> range2 = game.car_range(3);
        for (Obstacle.Car car: range) {
            assertTrue(car.getSpeed() > 0);
        }
        for (Obstacle.Car car: range2) {
            assertTrue(car.getSpeed() < 0);
        }
    }

    @Test
    void trunk_range() {
        Game game = new Game(45,16,19);
        ArrayList<Obstacle.Trunk> trunks1 = new ArrayList<Obstacle.Trunk>();
        trunks1.add(new Obstacle.Trunk(1, 1, 1, 45, 16, 1, 1, 1));
        // On vérifie le type
        assertEquals(trunks1.getClass(), game.trunk_range(2).getClass());
        // On vérifie que la liste a la bonne taille
        ArrayList<Obstacle.Trunk> range = game.trunk_range(2);
        System.out.println(range);
        assertTrue(range.size() == 2 || range.size() == 3);
        // On vérifie qu'en fonction de la parité la vitesse est correcte
        ArrayList<Obstacle.Trunk> range2 = game.trunk_range(3);
        for (Obstacle.Trunk trunk: range) {
            assertTrue(trunk.getSpeed() > 0);
        }
        for (Obstacle.Trunk trunk: range2) {
            assertTrue(trunk.getSpeed() < 0);
        }
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
        // On vérifie la taille
        assertEquals(10, game.allCars(10).size());
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
        // On vérifie la taille
        assertEquals(10, game.allCars(10).size());
    }

    @Test
    void move_allCars() {
        Game game = new Game(45,16,19);
        ArrayList<ArrayList<Obstacle.Car>> cars = game.allCars(2);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (ArrayList<Obstacle.Car> list_cars: cars) {
            for (Obstacle.Car car: list_cars) {
                list.add(car.getTop());
            }
        }
        // On vérifie que toutes les voitures de la liste bougent
        game.move_allCars(cars, 1);
        int i = 0;
        for (ArrayList<Obstacle.Car> list_car : cars) {
            for (Obstacle.Car car : list_car) {
                assertEquals(list.get(i) + 1, car.getTop());
                i+=1;
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

    @Test
    void reset_game() {
        Game game = new Game(45,16,19);
        game.reset_game();
        assertNull(game.getPlayerMode());
        assertNull(game.getDiff());
        assertFalse(game.getGameState());
        assertEquals(1, game.getRestart());
    }

    @Test
    void getPlayerMode() {
        Game game = new Game(45,16,19);
        assertNull(game.getPlayerMode());
    }

    @Test
    void setPlayerMode() {
        Game game = new Game(45,16,19);
        game.setPlayerMode("Mode 1");
        assertEquals("Mode 1", game.getPlayerMode());
    }

    @Test
    void getGrid() {
        Game game = new Game(45,16,19);
        assertEquals(45, game.getGrid());
    }

    @Test
    void setGrid() {
        Game game = new Game(45,16,19);
        game.setGrid(50);
        assertEquals(50, game.getGrid());
    }

    @Test
    void getRanges() {
        Game game = new Game(45,16,19);
        assertEquals(16, game.getRanges());
    }

    @Test
    void setRanges() {
        Game game = new Game(45,16,19);
        game.setRanges(25);
        assertEquals(25, game.getRanges());
    }

    @Test
    void getColumns() {
        Game game = new Game(45,16,19);
        assertEquals(19, game.getColumns());
    }

    @Test
    void setColumns() {
        Game game = new Game(45,16,19);
        game.setColumns(29);
        assertEquals(29, game.getColumns());
    }

    @Test
    void getGameState() {
        Game game = new Game(45,16,19);
        assertFalse(game.getGameState());
    }

    @Test
    void setGameState() {
        Game game = new Game(45,16,19);
        game.setGameState(true);
        assertTrue(game.getGameState());
    }

    @Test
    void getGame_width() {
        Game game = new Game(45,16,19);
        assertEquals(19*45, game.getGame_width());
    }

    @Test
    void setGame_width() {
        Game game = new Game(45,16,19);
        game.setGame_width(100);
        assertEquals(100, game.getGame_width());
    }

    @Test
    void getGame_height() {
        Game game = new Game(45,16,19);
        assertEquals(16*45, game.getGame_height());
    }

    @Test
    void setGame_height() {
        Game game = new Game(45,16,19);
        game.setGame_height(50);
        assertEquals(50, game.getGame_height());
    }

    @Test
    void getDiff() {
        Game game = new Game(45,16,19);
        assertNull(game.getDiff());
    }

    @Test
    void setDiff() {
        Game game = new Game(45,16,19);
        game.setDiff("easy");
        assertEquals("easy", game.getDiff());
    }

    @Test
    void getLeaderboard() {
        Game game = new Game(45,16,19);
        assertNull(game.getLeaderboard());
    }

    @Test
    void setLeaderboard() {
        Game game = new Game(45,16,19);
        game.setLeaderboard("easy");
        assertEquals("easy", game.getLeaderboard());
    }
    @Test
    void getRestart() {
        Game game = new Game(45,16,19);
        assertEquals(0, game.getRestart());
    }

    @Test
    void setRestart() {
        Game game = new Game(45,16,19);
        game.setRestart(1);
        assertEquals(1, game.getRestart());
    }

}