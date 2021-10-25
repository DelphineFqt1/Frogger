package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import processing.core.PApplet;
import GraphicalElements.Element;
import processing.core.PImage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static Util.UtilClass.record_treatment;

public class Main extends PApplet {

    public PApplet processing;

    Frog frog1;
    Frog frog2;
    ArrayList<ArrayList<Car>> cars;
    ArrayList<ArrayList<Trunk>> trunks;
    Element board;
    Game game;
    int ranges;
    int columns;
    int grid;
    int separate;
    float t1;
    float t2;
    float t_i;
    float t_fin;
    PImage im_frog;
    PImage im_frog2;
    PImage im_menu;
    PImage im_car_right;
    PImage im_car_left;
    PImage im_trunk;
    int PlayerMode;
    Path record;
    String remark = null;

    public static void main(String[] args) {
        PApplet.main(new String[]{Main.class.getName()});
    }

    @Override
    public void settings() {
        processing = this;
        PlayerMode =1;
        ranges = 16; // de préférence pair
        columns = 17;
        grid = 45;
        separate = ranges/2 ;
        board = new Element(processing);
        game = new Game(grid, ranges, columns, PlayerMode);
        size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {

        if (game.getPlayerMode()==2){
        frog1 = game.set_Frog2P1();
        frog2 = game.set_Frog2P2();
        }
        else {
            frog2 = game.set_Frog();
        }
        im_frog2 = loadImage("src\\main\\java\\Images\\frog2.png");
        im_frog = loadImage("src\\main\\java\\Images\\frog.png");
        im_menu = loadImage("src\\main\\java\\Images\\Menu.png");
        im_menu.resize(game.getGame_width(), game.getGame_height());
        record = Paths.get("src\\main\\java\\GameCommons\\Record_hard");
        im_car_right = loadImage("src\\main\\java\\Images\\car_right.png");
        im_car_left = loadImage("src\\main\\java\\Images\\car_left.png");
        im_trunk = loadImage("src\\main\\java\\Images\\trunk.png");
        cars = game.allCars(separate);
        trunks = game.allTrunks(separate+2, ranges-2);
    }

    @Override
    public void draw() {
        if (millis() < 3000) {
            background(im_menu);
            t1 = millis();
        } else {
            background(0);
            t_i = (millis() - t1) / 1000;


            board.create_case(0, (separate - 1) * grid, game.getGame_width(), game.getGame_height() - grid, 20, 20, 30);
            board.create_case(0, (separate - 2) * grid, game.getGame_width(), grid, 0, 50, 100);
            board.create_case(0, grid, game.getGame_width(), 0, 0, 200, 0);

            if (game.getPlayerMode() == 2) {

                for (ArrayList<Car> range_i : cars) {
                    for (Car car : range_i) {
                        car.move(car.getSpeed(), 0);
                        if (car.getSpeed() > 0) {
                            board.show_car(car, im_car_right);
                        }
                        else {
                            board.show_car(car, im_car_left);
                        }
                        if (frog1.intersect(car)) {
                            frog1.setCar_intersection(true);
                        }
                        if (frog2.intersect(car)) {
                            frog2.setCar_intersection(true);
                        }
                    }
                }
                int count_inter = 0;
                int count2 = 0;
                for (ArrayList<Trunk> range_i : trunks) {
                    for (Trunk trunk : range_i) {
                        trunk.move(trunk.getSpeed(), 0);
                        board.show_trunk(trunk, im_trunk);
                        if (frog1.intersect(trunk)) {
                            count_inter++;
                            if (count_inter <= 1) {
                                frog1.setLeft(frog1.getLeft() + trunk.getSpeed());
                                frog1.setRight(frog1.getRight() + trunk.getSpeed());
                            }
                        }
                        if (frog2.intersect(trunk)) {
                            count2++;
                            if (count2 <= 1) {
                                frog2.setLeft(frog2.getLeft() + trunk.getSpeed());
                                frog2.setRight(frog2.getRight() + trunk.getSpeed());
                            }
                        }
                    }
                }


                if (frog1.getRange() > (separate + 1) && frog1.getRange() < ranges - 1 && count_inter == 0) {
                    frog1.setTrunk_intersection(false);
                }
                if (frog2.getRange() > (separate + 1) && frog2.getRange() < ranges - 1 && count2 == 0) {
                    frog2.setTrunk_intersection(false);
                }
                board.show_frog(frog1, im_frog);
                board.show_frog(frog2, im_frog2);
                game.deal_state_frog2P1(frog1);
                game.deal_state_frog2P2(frog2);

            } else {
                for (ArrayList<Car> range_i : cars) {
                    for (Car car : range_i) {
                        car.move(car.getSpeed(), 0);
                        if (car.getSpeed() > 0) {
                            board.show_car(car, im_car_right);
                        }
                        else {
                            board.show_car(car, im_car_left);
                        }
                        if (frog2.intersect(car)) {
                            frog2.setCar_intersection(true);
                        }
                    }
                }
                int count2 = 0;
                for (ArrayList<Trunk> range_i : trunks) {
                    for (Trunk trunk : range_i) {
                        trunk.move(trunk.getSpeed(), 0);
                        board.show_trunk(trunk, im_trunk);
                        if (frog2.intersect(trunk)) {
                            count2++;
                            if (count2 <= 1) {
                                frog2.setLeft(frog2.getLeft() + trunk.getSpeed());
                                frog2.setRight(frog2.getRight() + trunk.getSpeed());
                            }
                        }
                    }
                }

                if (frog2.getRange() > (separate + 1) && frog2.getRange() < ranges - 1 && count2 == 0) {
                    frog2.setTrunk_intersection(false);
                }
                board.show_frog(frog2, im_frog2);
                game.deal_state_frog(frog2);


            }
            board.create_text(t_i + "s", 20, grid / 2, grid / 2, 0, 0, 0);


            if (game.getGameState()) {
                t2 = millis();
                t_fin = (t2 - t1) / 1000;

                board.create_text("Congratulations ! You beat Frogger in " + t_fin + "s.", 32, game.getGame_width() / 2 - 6 * grid, game.getGame_height() / 2, 255, 255, 255);
                remark = record_treatment(record, t_fin);
                board.create_text(remark, 32, game.getGame_width() / 2 - 6 * grid, game.getGame_height() / 2 + grid, 255, 255, 0);
                processing.stop();

            }
        }
    }


    @Override
    public void keyPressed() {
        if (keyCode == UP){
            frog2.move(0,-grid);
            frog2.setRange(frog2.getRange()+1);
            //System.out.println("bottom :" + frog.getBottom());

        }
        else if (keyCode == DOWN){
            frog2.move(0,grid);
            frog2.setRange(frog2.getRange()-1);
        }
        else if (keyCode == RIGHT) {
            frog2.move(grid, 0);
        }
        else if (keyCode == LEFT){
            frog2.move(-grid,0);

        }
        try {
             if (keyCode == 90) {
                frog1.move(0, -grid);
                frog1.setRange(frog1.getRange() + 1);


            } else if (keyCode == 83) {
                frog1.move(0, grid);
                frog1.setRange(frog1.getRange() - 1);


            } else if (keyCode == 68) {
                frog1.move(grid, 0);

            } else if (keyCode == 81) {
                frog1.move(-grid, 0);

            }
        }
        catch (NullPointerException e){
            System.out.println("Le joueur ne peut pas utiliser cette partie du clavier");
        }
    }
}
