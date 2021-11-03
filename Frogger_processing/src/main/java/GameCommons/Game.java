package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import java.util.ArrayList;


import static Util.UtilClass.random_btw;

/**
 * La classe Game centralise les paramètres du jeu ainsi que les méthodes qui contrôlent son bon déroulement
 */
public class Game implements IFrog, IEnvironment {
    private int grid;
    private int ranges;
    private int columns;
    private boolean gameState;
    private int game_width;
    private int game_height;
    private String PlayerMode;
    private String Diff;
    private String leaderboard;

    public Game(int grid, int ranges, int columns) {
        this.grid = grid;
        this.ranges = ranges;
        this.columns = columns;
        this.game_width = columns * grid;
        this.game_height = ranges * grid;
        this.gameState = false;
        this.PlayerMode = null;
        this.Diff = null;
        this.leaderboard=null;
        if (ranges <=0 || columns<=0 || grid<=0){
            this.ranges = 16;
            this.columns = 19;
            this.grid = 45;
            this.game_width = this.columns*this.grid;
            this.game_height = this.ranges*this.grid;
        }
    }

    @Override
    public ArrayList<Car> car_range(int range) {
        int x = random_btw(0, getGame_width());
        int number = random_btw(2, 3);
        int speed;
        int k = random_btw(2, 3);
        if(range%2==0) {
            speed = k;
        }
        else {
            speed = -k;
        }

        ArrayList<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < number; i++) {
            int size = random_btw(2, 3) * grid;
            if (speed<0) {
                x = x - size - Math.abs(speed) * grid;
            }
            else {
                x = x + size + Math.abs(speed) * grid;
            }

            cars.add(new Car(x, game_height - (range + 1) * grid, size, grid, range, speed, game_width, game_height));

        }
        return cars;
    }

    @Override
    public ArrayList<Trunk> trunk_range(int range) {
        int x = random_btw(0, getGame_width());
        int size = 3 * grid;

        int speed;
        int number = random_btw(1, 3);
        int k = random_btw(3, 5);
        if(range%2==0) {
            speed = k;
        }
        else {
            speed = -k;
        }

        ArrayList<Trunk> trunks = new ArrayList<Trunk>();
        for (int i = 0; i < number; i++) {
            x = x - size - Math.abs(speed) * grid;
            trunks.add(new Trunk(x, game_height - (range + 1) * grid, size, grid, range, speed, game_width, game_height));

        }
        return trunks;
    }

    @Override
    public ArrayList<ArrayList<Car>> allCars(int range_end) {
        ArrayList<ArrayList<Car>> allCars = new ArrayList<ArrayList<Car>>();
        for (int i = 1; i<= range_end; i++){
            allCars.add(car_range(i));
        }
        return allCars;
    }

    @Override
    public ArrayList<ArrayList<Trunk>> allTrunks(int range_begin, int range_end) {
        ArrayList<ArrayList<Trunk>> allTrunks = new ArrayList<ArrayList<Trunk>>();
        for (int i = range_begin; i<= range_end; i++){
            allTrunks.add(trunk_range(i));
        }
        return allTrunks;
    }

    @Override
    public void move_allCars(ArrayList<ArrayList<Car>> allCars, int ydir) {
        for (ArrayList<Car> range_i : allCars){
            for (Car car : range_i){
                car.move(0, ydir);
            }
        }
    }

    @Override
    public void reset_game() {
        setPlayerMode(null);
        setDiff(null);
        setGameState(false);
    }


    @Override
    public Frog setFrog(int num) {
        if (PlayerMode =="1 PLAYER"){
            return new Frog(this.game_width / 2 - grid / 2, this.game_height - grid, grid);
        }
        else{
            if (num==1){
                return new Frog(this.game_width / 4, this.game_height - grid, grid);
            }
            else {
                return new Frog(3*this.game_width / 4, this.game_height - grid, grid);
            }
        }
    }

    @Override
    public void reset_Frog(Frog frog,  int num) {
        if (PlayerMode == "1 PLAYER"){
            frog.setLeft(columns * grid / 2 - grid / 2);
            frog.setRight(columns * grid / 2 - grid / 2 + grid);
        }
        else{
            if (num==1){
                frog.setLeft(columns * grid / 4);
                frog.setRight(columns * grid / 4 + grid);
            }
            else {
                frog.setLeft(3*columns * grid / 4);
                frog.setRight(3*columns * grid / 4+ grid);
            }
        }
        frog.setBottom(ranges * grid - grid);
        frog.setTop(ranges * grid);
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);
    }

    @Override
    public void stateFrog(Frog frog, int num) {
        if ((frog.isCar_intersection() == frog.isTrunk_intersection()) || (frog.getLeft() < 0 || frog.getRight() > this.game_width || frog.getBottom() < 0 || frog.getTop() > this.game_height)) {
            reset_Frog(frog, num);
            frog.setGAMEOVER(true);
        }
        if (frog.getRange() >= this.getRanges() - 1) {
            setGameState(true);
        }
    }


    public String getPlayerMode() {
        return PlayerMode;
    }

    public void setPlayerMode(String playerMode) {
        PlayerMode = playerMode;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public int getRanges() {
        return ranges;
    }

    public void setRanges(int ranges) {
        this.ranges = ranges;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public boolean getGameState() {
        return gameState;
    }

    public void setGameState(boolean gameState) {
        this.gameState = gameState;
    }

    public int getGame_width() {
        return game_width;
    }

    public void setGame_width(int game_width) {
        this.game_width = game_width;
    }

    public int getGame_height() {
        return game_height;
    }

    public void setGame_height(int game_height) {
        this.game_height = game_height;
    }

    public String getDiff() {return Diff;}

    public void setDiff(String diff) {Diff = diff;}

    public String getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(String leaderboard) {
        this.leaderboard = leaderboard;
    }
}


