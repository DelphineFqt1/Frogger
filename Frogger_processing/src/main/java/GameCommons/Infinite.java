package GameCommons;

import processing.core.PApplet;
import MovingElements.Frog;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import GraphicalElements.Element;
import processing.core.PImage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import static Util.UtilClass.endless_treatment;


public class Infinite extends PApplet {
    public PApplet inf_processing;
    Frog frog2;
    ArrayList<ArrayList<Car>> cars;
    Element board;
    Game game;
    int ranges;
    int columns;
    int grid;
    int separate;
    PImage im_frog2;
    PImage im_car;
    PImage im_rev;
    String PlayerMode;
    String Diff;
    Path record_endless;
    String remark = null;
    int count50 = 50;



    public static void main(String[] args) {
        PApplet.main(new String[]{Infinite.class.getName()});
    }

    @Override
    public void settings() {
        inf_processing = this;
        PlayerMode = "1 PLAYER";
        ranges = 16; // de préférance pair
        columns = 19;
        grid = 45;
        separate = ranges / 2;
        board = new Element(inf_processing);
        game = new Game(grid, ranges, columns);
        game.setPlayerMode(PlayerMode);
        size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {

        frog2 = game.setFrog(1);
        im_frog2 = loadImage("" +
                "src/main/java/Images/frog2.png");
        im_car = loadImage("" +
                "src/main/java/Images/car_left.png");
        im_rev = loadImage("" +
                "src/main/java/Images/car_right.png");
        cars = game.allCars(ranges);
        record_endless = Paths.get("" +
                "src/main/java/GameCommons/Record_endless");
    }

    @Override
    public void draw() {
        board.background(50);
        board.show_image(im_frog2, frog2.getLeft(), frog2.getBottom(), frog2.getWidth(), frog2.getHeight());
        for (ArrayList<Car> range_i : cars) {
            for (Car car : range_i) {
                car.move(car.getSpeed(), 0);
                if (car.getSpeed() > 0) {
                    board.show_image(im_rev, car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight());
                } else {
                    board.show_image(im_car, car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight());
                }
                if (frog2.intersect(car)) {
                    frog2.setCar_intersection(true);
                }
            }
        }

        float k = frog2.getRange();

        if (k==count50){
            for (ArrayList<Car> range_i : cars) {
                for (Car car : range_i) {
                    if (car.getSpeed() > 0) {
                        car.setSpeed(car.getSpeed() + 1);
                    } else {
                        car.setSpeed(car.getSpeed() - 1);
                    }
                }
            }
            count50 += 50;
        }



        game.stateFrog(frog2,1);

        board.create_text("Score : "+k, 20, grid/2, grid/2, 255, 255, 255);
        if (frog2.isGAMEOVER()) {
            remark = endless_treatment(record_endless,k);
            board.create_text("Your score is " + k, 32, game.getGame_width() / 2 - 2*grid, game.getGame_height() / 2, 0, 255, 0);
            board.create_text(remark, 32, game.getGame_width() / 2 -  2*grid, game.getGame_height() / 2 + grid, 0, 255, 0);
            inf_processing.stop();
        }
    }


    @Override
    public void keyPressed() {
        if (keyCode == UP) {
            game.move_allCars(cars, grid);
            frog2.setRange(frog2.getRange() + 1);
        }
        else if (keyCode == RIGHT) {
            frog2.move(grid, 0);

        } else if (keyCode == LEFT) {
            frog2.move(-grid, 0);
        }
    }
}

