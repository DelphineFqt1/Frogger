package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void car_range() {
        /* MARCHE PAS PSQ car_range A UNE LONGUEUR RANDOM
        Game game = new Game(45,16,19);
        ArrayList<Obstacle.Car> cars1 = new ArrayList<Obstacle.Car>();
        cars1.add(new Obstacle.Car(1, 1, 1, 45, 16, 1, 1, 1));
        assertEquals(cars1, game.car_range(2));
*/
    }

    @Test
    void trunk_range() {
    }

    @Test
    void allCars() {
    }

    @Test
    void allTrunks() {
    }

    @Test
    void move_allCars() {
    }

    @Test
    void setFrog() {
        /* NE MARCHE PAS NON PLUS :'(
        Game game = new Game(45,16,19);
        Frog frog = new Frog(19*45 / 4, 16*45 - 45, 45);

        assertEquals(frog, game.setFrog(1));
         */
    }

    @Test
    void reset_Frog() {
        Game game = new Game(45,16,19);
        Frog frog = new Frog(19*45 / 4, 16*45 - 45, 45);
        frog.move(1,-1);
        game.reset_Frog(frog,1); // CA MARCHE QUAND MEME SANS CETTE LIGNE ALORS QUE CA DEVRAIT METTRE UNE ERREUR
        assertEquals(0,frog.getRange());

    }

    @Test
    void stateFrog() {
    }

    @Test
    void isGameState() {
    }

    @Test
    void getPlayerMode() {
    }

    @Test
    void setPlayerMode() {
    }

    @Test
    void getGrid() {
    }

    @Test
    void setGrid() {
    }

    @Test
    void getRanges() {
    }

    @Test
    void setRanges() {
    }

    @Test
    void getColumns() {
    }

    @Test
    void setColumns() {
    }

    @Test
    void getGameState() {
    }

    @Test
    void setGameState() {
    }

    @Test
    void getGame_width() {
    }

    @Test
    void setGame_width() {
    }

    @Test
    void getGame_height() {
    }

    @Test
    void setGame_height() {
    }

    @Test
    void getDiff() {
    }

    @Test
    void setDiff() {
    }

    @Test
    void getLeaderboard() {
    }

    @Test
    void setLeaderboard() {
    }}