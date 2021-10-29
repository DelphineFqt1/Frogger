package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


import static Util.UtilClass.random_btw;


public class Game implements IFrog, IEnvironment {
    private int grid;
    private int ranges;
    private int columns;
    private boolean gameState;
    private int game_width;
    private int game_height;
    private int PlayerMode;
    private String Mode1;
    private String Mode2;
    private String Mode3;
    private String Diff;


    public Game(int grid, int ranges, int columns, int PlayerMode, String Diff) {
        this.grid = grid;
        this.ranges = ranges;
        this.columns = columns;
        this.game_width = columns * grid;
        this.game_height = ranges * grid;
        this.gameState = false;
        this.PlayerMode = PlayerMode;
        this.Diff = Diff;
        setMode1(null);
        setMode2(null);
        setMode3(null);
    }

    public boolean isGameState() {
        return gameState;
    }

    public int getPlayerMode() {
        return PlayerMode;
    }

    public void setPlayerMode(int playerMode) {
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

    public String getMode1() {return Mode1; }

    public void setMode1(String mode1) {Mode1 = mode1; }

    public String getMode2() {return Mode2; }

    public void setMode2(String mode2) {Mode2 = mode2; }

    public String getDiff() {return Diff;}

    public void setDiff(String diff) {Diff = diff;}

    public String getMode3() {return Mode3;}

    public void setMode3(String mode3) {Mode3 = mode3;}


    @Override
    public ArrayList<Car> car_range(int range) {
        int x = random_btw(0, getGame_width());
        int number = random_btw(2, 3);
        int speed;
        int k = random_btw(0, 1);
        if(range%2==0) {
            if (k == 0) {
                speed = 1;
            } else {
                speed = 2;
            }
        }
        else {
            if (k == 0) {
                speed = -1;
            } else {
                speed = -2;
            }
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
        int number = 3;
        int k = random_btw(0, 1);
        if(range%2==0) {
            if (k == 0) {
                speed = 3;
            } else {
                speed = 4;
            }
        }
        else {
            if (k == 0) {
                speed = -3;
            } else {
                speed = -4;
            }
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
    public void move_allCars(ArrayList<ArrayList<Car>> allCars, float ydir) {
        for (ArrayList<Car> range_i : allCars){
            for (Car car : range_i){
                car.move(0, ydir);
            }
        }
    }


    @Override
    public void deal_state_frog(Frog frog) {
        if ((frog.isCar_intersection() == frog.isTrunk_intersection()) || (frog.getLeft() < 0 || frog.getRight() > this.game_width || frog.getBottom() < 0 || frog.getTop() > this.game_height)) {
            resetFrog(frog);
            frog.setGAMEOVER(true);
        }

        if (frog.getRange() >= this.getRanges() - 1) {
            setGameState(true);
        }
    }


    @Override
    public void resetFrog(Frog frog) {
        frog.setLeft(columns * grid / 2 - grid / 2);
        frog.setRight(columns * grid / 2 - grid / 2 + grid);
        frog.setBottom(ranges * grid - grid);
        frog.setTop(ranges * grid);
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);

    }

    @Override
    public Frog set_Frog() {
        Frog frog = new Frog(this.game_width / 2 - grid / 2, this.game_height - grid, grid);
        return frog;
    }

    @Override
    public Frog set_Frog2P1() {
        Frog frog = new Frog(getGame_width() / 4, this.game_height - grid, grid);
        return frog;
    }

    @Override
    public Frog set_Frog2P2() {
        Frog frog = new Frog(3 * getGame_width() / 4, this.game_height - grid, grid);
        return frog;
    }

    @Override
    public void resetFrog2P1(Frog frog) {
        frog.setLeft(getGame_width() / 4);
        frog.setRight(getGame_width() / 4 + grid);
        frog.setBottom(ranges * grid - grid);
        frog.setTop(ranges * grid);
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);
    }

    @Override
    public void resetFrog2P2(Frog frog) {
        frog.setLeft(3 * getGame_width() / 4);
        frog.setRight(3 * getGame_width() / 4 + grid);
        frog.setBottom(ranges * grid - grid);
        frog.setTop(ranges * grid);
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);

    }

    @Override
    public void deal_state_frog2P1(Frog frog) {
        if ((frog.isCar_intersection() == frog.isTrunk_intersection()) || (frog.getLeft() < 0 || frog.getRight() > this.game_width || frog.getBottom() < 0 || frog.getTop() > this.game_height)) {
            resetFrog2P1(frog);
            frog.setGAMEOVER(true);
        }
        if (frog.getRange() >= this.getRanges() - 1) {
            setGameState(true);
        }
    }

    @Override
    public void deal_state_frog2P2(Frog frog) {
        if ((frog.isCar_intersection() == frog.isTrunk_intersection()) || (frog.getLeft() < 0 || frog.getRight() > this.game_width || frog.getBottom() < 0 || frog.getTop() > this.game_height)) {
            resetFrog2P2(frog);
            frog.setGAMEOVER(true);
        }
        if (frog.getRange() >= this.getRanges() - 1) {
            setGameState(true);
        }
    }

    @Override
    public Frog setFrog(int num) {
        if (Mode1 =="1 PLAYER"){
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
        if (Mode1 == "1 PLAYER"){
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


}


