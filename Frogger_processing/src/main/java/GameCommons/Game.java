package GameCommons;

import MovingElements.Obstacle;
import processing.core.PApplet;
import MovingElements.Frog;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Game implements IFrog, IEnvironment {
    private int grid;
    private int ranges;
    private int columns;
    private boolean gameState;
    private int game_width;
    private int game_height;
    private int PlayerMode;


    public Game(int grid, int ranges, int columns, int PlayerMode) {
        this.grid = grid;
        this.ranges = ranges;
        this.columns = columns;
        this.game_width = columns * grid;
        this.game_height = ranges * grid;
        this.gameState = false;
        this.PlayerMode = PlayerMode;
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

    @Override
    public ArrayList<Car> car_range(int range) {
        int x = random_btw(0, getGame_width());
        int number = random_btw(1, 3);
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
            int size = random_btw(1, 3) * grid;
            x = x - size - Math.abs(speed) * grid;

            cars.add(new Car(x, game_height - (range + 1) * grid, size, grid, range, speed, game_width));

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

        ArrayList<Trunk> trunks = new ArrayList<Trunk>();
        for (int i = 0; i < number; i++) {
            x = x - size - Math.abs(speed) * grid;
            trunks.add(new Trunk(x, game_height - (range + 1) * grid, size, grid, range, speed, game_width));

        }
        return trunks;
    }

    @Override
    public int random_btw(int begin, int end) {
        Random rand = new Random();
        return rand.nextInt(end - begin + 1) + begin;
    }


    @Override
    public void deal_state_frog(Frog frog) {
        if ((frog.isCar_intersection() == frog.isTrunk_intersection()) || (frog.getLeft() < 0 || frog.getRight() > this.game_width || frog.getBottom() < 0 || frog.getTop() > this.game_height)) {
            resetFrog(frog);
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
        }
        if (frog.getRange() >= this.getRanges() - 1) {
            setGameState(true);
        }
    }

    @Override
    public void deal_state_frog2P2(Frog frog) {
        if ((frog.isCar_intersection() == frog.isTrunk_intersection()) || (frog.getLeft() < 0 || frog.getRight() > this.game_width || frog.getBottom() < 0 || frog.getTop() > this.game_height)) {
            resetFrog2P2(frog);
        }
        if (frog.getRange() >= this.getRanges() - 1) {
            setGameState(true);
        }
    }

    @Override
    public String record_treatment(Path path, float t) {
        String remark = null;
        File file = new File(path.toString());
        ArrayList<String> Snumbers = new ArrayList<String>();
        ArrayList<String> NSnumbers = new ArrayList<String>();
        try{

        float[] numbers = new float[10];
            for (String ligne : Files.readAllLines(path)) {
                Snumbers.add(ligne.substring(3, ligne.length()-1));
                //System.out.println(ligne.substring(3, ligne.length()-1));
                }
            for (int i =0; i<Snumbers.size(); i++){
                numbers[i]= Float.parseFloat(Snumbers.get(i));
                //System.out.println(numbers[i]);
            }
            numbers[9] =t;
            java.util.Arrays.sort(numbers);
            if (numbers[0]==t){
                remark = "You ranked first ! (°0°)";
            }
            else if (numbers[9]!=t){
                remark = "You are in the leaderboard ! (=^_^=)";
            }
            else {
                remark = "But you didn't rank in the leaderboard (>_<)";
            }
            System.out.println("LEADERBOARD : ");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            for (int i=0; i<9; i++){
                int k = i+1;
                NSnumbers.add(k +". " + numbers[i] + "s\n" );
                System.out.print(NSnumbers.get(i));
                fw.write(NSnumbers.get(i));
            }
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return remark;
    }
}
