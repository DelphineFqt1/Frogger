package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import processing.core.PApplet;
import GraphicalElements.Element;
import processing.core.PImage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Util.UtilClass.record_treatment;

public class Beta extends PApplet {

    Frog frog1;
    Frog frog2;
    ArrayList<ArrayList<Car>> cars;
    ArrayList<ArrayList<Trunk>> trunks;
    Element board;
    Game game;
    String playermode;
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
    Path record;
    String remark = null;

    public static void main(String[] args) {
        PApplet.main(new String[]{Beta.class.getName()});
    }

    @Override
    public void settings() {

        ranges = -16; // de préférence pair
        columns = -19;
        grid = -45;
        playermode = "2 PLAYERS";

        board = new Element(this);
        game = new Game(grid, ranges, columns);
        game.setPlayerMode(playermode);
        separate = game.getRanges()/2 ;
        board.size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {
        String im = "src/main/java/Resources/Images";
        String rec = "src/main/java/Resources/Records";
        frog1 = game.setFrog(1);
        frog2 = game.setFrog(2);

        im_frog2 = loadImage(im + "/frog2.png");
        im_frog = loadImage(im + "/frog.png");
        im_menu = loadImage(im + "/Menu.png");
        record = Paths.get(rec + "/Record_easy");
        im_car_right = loadImage(im + "/car_right.png");
        im_car_left = loadImage(im + "/car_left.png");
        im_trunk = loadImage(im + "/trunk.png");
        cars = game.allCars(separate);
        trunks = game.allTrunks(separate+2, game.getRanges()-2);
    }

    @Override
    public void draw() {
        if (millis() < 3000) {
            board.background_im(im_menu, game.getGame_width(), game.getGame_height());
            t1 = millis();
        } else {
            background(0);
            t_i = (millis() - t1) / 1000;


            board.create_case(0, (separate - 1) * game.getGrid(), game.getGame_width(), game.getGame_height() - game.getGrid(), 20, 20, 30);
            board.create_case(0, (separate - 2) * game.getGrid(), game.getGame_width(), game.getGrid(), 0, 50, 100);
            board.create_case(0, game.getGrid(), game.getGame_width(), 0, 0, 200, 0);

            if (game.getPlayerMode() == "2 PLAYERS") {

                for (ArrayList<Car> range_i : cars) {
                    for (Car car : range_i) {
                        car.move(car.getSpeed(), 0);
                        if (car.getSpeed() > 0) {
                            board.show_image(im_car_right, car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight());
                        }
                        else {
                            board.show_image(im_car_left, car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight());
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
                        board.show_image(im_trunk, trunk.getLeft(), trunk.getBottom(), trunk.getWidth(), trunk.getHeight());
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


                if (frog1.getRange() > (separate + 1) && frog1.getRange() < game.getRanges() - 1 && count_inter == 0) {
                    frog1.setTrunk_intersection(false);
                }
                if (frog2.getRange() > (separate + 1) && frog2.getRange() < game.getRanges() - 1 && count2 == 0) {
                    frog2.setTrunk_intersection(false);
                }
                board.show_image(im_frog, frog1.getLeft(), frog1.getBottom(), frog1.getWidth(), frog1.getHeight());
                board.show_image(im_frog2, frog2.getLeft(), frog2.getBottom(), frog2.getWidth(), frog2.getHeight());
                game.stateFrog(frog1,1);
                game.stateFrog(frog2, 2);

            } else {
                for (ArrayList<Car> range_i : cars) {
                    for (Car car : range_i) {
                        car.move(car.getSpeed(), 0);
                        if (car.getSpeed() > 0) {
                            board.show_image(im_car_right, car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight());
                        }
                        else {
                            board.show_image(im_car_left,car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight() );
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
                        board.show_image(im_trunk, trunk.getLeft(), trunk.getBottom(), trunk.getWidth(), trunk.getHeight());
                        if (frog2.intersect(trunk)) {
                            count2++;
                            if (count2 <= 1) {
                                frog2.setLeft(frog2.getLeft() + trunk.getSpeed());
                                frog2.setRight(frog2.getRight() + trunk.getSpeed());
                            }
                        }
                    }
                }

                if (frog2.getRange() > (separate + 1) && frog2.getRange() < game.getRanges() - 1 && count2 == 0) {
                    frog2.setTrunk_intersection(false);
                }
                board.show_image(im_frog2, frog2.getLeft(), frog2.getBottom(), frog2.getWidth(), frog2.getHeight());
                game.stateFrog(frog2, 1);


            }
            board.create_text(t_i + "s", 20, game.getGrid() / 2, game.getGrid() / 2, 0, 0, 0);


            if (game.getGameState()) {
                t2 = millis();
                t_fin = (t2 - t1) / 1000;

                board.create_text("Congratulations ! You beat Frogger in " + t_fin + "s.", 32, game.getGame_width() / 2 - 6 * game.getGrid(), game.getGame_height() / 2, 255, 255, 255);
                remark = record_treatment(record, t_fin);
                board.create_text(remark, 32, game.getGame_width() / 2 - 6 * game.getGrid(), game.getGame_height() / 2 + game.getGrid(), 255, 255, 0);
                this.stop();

            }
        }
    }


    @Override
    public void keyPressed() {
        if (keyCode == UP){
            frog2.move(0,-game.getGrid());

        }
        else if (keyCode == DOWN){
            frog2.move(0,game.getGrid());
        }
        else if (keyCode == RIGHT) {
            frog2.move(game.getGrid(), 0);
        }
        else if (keyCode == LEFT){
            frog2.move(-game.getGrid(),0);

        }
        try {
             if (keyCode == 90) {
                frog1.move(0, -game.getGrid());


            } else if (keyCode == 83) {
                frog1.move(0, game.getGrid());


            } else if (keyCode == 68) {
                frog1.move(game.getGrid(), 0);

            } else if (keyCode == 81) {
                frog1.move(-game.getGrid(), 0);

            }
        }
        catch (NullPointerException e){
            System.out.println("Le joueur ne peut pas utiliser cette partie du clavier");
        }
    }
}