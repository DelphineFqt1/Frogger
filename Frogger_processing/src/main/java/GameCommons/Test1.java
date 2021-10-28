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
import java.util.Objects;
import java.util.Scanner;

import static Util.UtilClass.endless_treatment;
import static Util.UtilClass.record_treatment;

public class Test1 extends PApplet {

    public PApplet processing;

    Frog frog1;
    Frog frog2;
    ArrayList<ArrayList<Car>> cars;
    ArrayList<ArrayList<Trunk>> trunks;
    ArrayList<String> scores;
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
    PImage im_cursor;
    PImage im_car_right;
    PImage im_car_left;
    PImage im_trunk;
    PImage im_rb_pepe_right;
    PImage im_rb_pepe_left;
    PImage im_cup;
    PImage im_back_arrow;
    PImage im_leaderboard_easy;
    PImage im_leaderboard_hard;
    PImage im_leaderboard_infinity;
    int PlayerMode;
    String Diff;
    Path record;
    Path record_hard;
    Path record_infinity;
    String remark = null;
    int count50 = 50;
    float score_inf;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PApplet.main(new String[]{Test1.class.getName()});
    }

    @Override
    public void settings() {
        processing = this;
//        PlayerMode = 1;
//        Diff = "EASY";
        ranges = 16; // de préférence pair
        columns = 19;
        grid = 45;
        separate = ranges/2 ;
        board = new Element(processing);
        game = new Game(grid, ranges, columns, PlayerMode, Diff);
        size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {
/*
        if (game.getPlayerMode()==2){
            frog1 = game.set_Frog2P1();
            frog2 = game.set_Frog2P2();
        }
        else {
            frog2 = game.set_Frog();
        }
 */       im_frog2 = loadImage("src/main/java/Images/frog2.png");
        im_frog = loadImage("src/main/java/Images/frog.png");
        im_menu = loadImage("src/main/java/Images/Menu.png");
        im_menu.resize(game.getGame_width(), game.getGame_height());
        record = Paths.get("src/main/java/GameCommons/Record_easy");
        record_hard = Paths.get("src/main/java/GameCommons/Record_hard");
        record_infinity = Paths.get("src/main/java/GameCommons/Record_endless");
        im_car_right = loadImage("src/main/java/Images/car_right.png");
        im_car_left = loadImage("src/main/java/Images/car_left.png");
        im_trunk = loadImage("src/main/java/Images/trunk.png");
        im_cursor = loadImage("src/main/java/Images/cursor.png");
        im_rb_pepe_right = loadImage("src/main/java/Images/rb_pepe_right.jpg");
        im_rb_pepe_left = loadImage("src/main/java/Images/rb_pepe_left.png");
        im_cup = loadImage("src/main/java/Images/cup.png");
        im_back_arrow = loadImage("src/main/java/Images/back_arrow.png");
        im_leaderboard_easy = loadImage("src/main/java/Images/leaderboard_easy.png");
        im_leaderboard_hard = loadImage("src/main/java/Images/leaderboard_hard.png");
        im_leaderboard_infinity = loadImage("src/main/java/Images/leaderboard_infinity.png");
/*        if (game.getDiff() == "HARD") {
            cars = game.allCars(separate);
            trunks = game.allTrunks(separate + 2, ranges - 2);
        } else if (game.getDiff() == "EASY"){
            cars = game.allCars(ranges - 2);
        }
*/
    }

    @Override
    public void draw() {
        if (game.getDiff() == null) {   // ECRAN D'ACCUEIL + CHOIX DU PLAYERMODE ET DU NIVEAU DE DIFFICULTE
            //if (millis()<7000){

            // ON AFFICHE LES PREMIERS BOUTONS

            if (game.getMode1() == null) {

                background(im_menu);
                board.create_case(77, 600, 277, 550, 255, 255, 255);
                board.image(im_frog,82,558, 40, 40);
                board.create_text("1 PLAYER", 25, 130 , 585, 0, 0, 0);
                board.create_case(327, 600, 527, 550, 255, 255, 255);
                board.image(im_frog,332,558, 38, 38);
                board.image(im_frog2,485,558, 38, 38);
                board.create_text("2 PLAYERS", 25, 370, 585, 0, 0, 0);
                board.create_case(577,600 , 777, 550, 255, 255, 255);
                board.create_text("LEADERBOARD", 25, 615, 585, 0, 0, 0);
                board.image(im_cup,580,558, 38, 38);
            }

// ACTION LORSQUE L'ON CLIQUE SUR "1 PLAYER" : ON AFFICHE LE MEME MENU MAIS AVEC LES BOUTONS "EASY" et "HARD"
            if ((mouseX < 277) && (mouseX > 77) && (mouseY < 600) && (mouseY > 550) && (mousePressed) && game.getMode1() == null) {
                game.setMode1("1 PLAYER");

            }
            if ((mouseX < 527) && (mouseX > 327) && (mouseY < 600) && (mouseY > 550) && (mousePressed) && game.getMode1() == null) {
                game.setMode1("2 PLAYERS");

            }
            if ((mouseX < 777) && (mouseX > 577) && (mouseY < 600) && (mouseY > 550) && (mousePressed) && game.getMode1() == null) {
                game.setMode1("LEADERBOARD");

            }

            // ON AFFICHE LES BOUTONS DU MODE DE JEU (ROUTE, ROUTE + RIVIERE, INFINI)
            if (game.getMode1() == "1 PLAYER") {
                game.setPlayerMode(1);
                background(im_menu);
                board.create_case(77, 550, 277, 500, 255, 255, 255);
                board.create_text("EASY", 25, 150, 535, 0, 0, 0);
                board.image(im_car_right, 95, 508, 38, 38);
                board.image(im_car_left, 220, 508, 38, 38);
                board.create_case(327, 550, 527, 500, 255, 255, 255);
                board.create_text("HARD", 25, 395, 535, 0, 0, 0);
                board.image(im_car_right, 350, 508, 38, 38);
                board.image(im_trunk, 465, 517, 38, 20);
                board.create_case(577, 550, 777, 500, 255, 255, 255);
                board.create_text("INFINITY", 25, 630, 535, 0, 0, 0);
                board.image(im_rb_pepe_right, 585, 508, 38, 38);
                board.image(im_rb_pepe_left, 730, 508, 38, 38);
                board.create_case(670, 700, 800, 660, 255, 255, 255);
                board.create_text("BACK", 25, 720, 690, 0, 0, 0);
                board.image(im_back_arrow, 680, 662, 38, 38);
            }

// Après avoir choisi le mode 1 joueur, on choisit le niveau de difficulté
            if ((mouseX < 277) && (mouseX > 77) && (mouseY < 550) && (mouseY > 500) && (mousePressed) && game.getMode1() == "1 PLAYER") {
                game.setDiff("EASY");
                // System.out.print(game.getDiff());
            }
            if ((mouseX < 527) && (mouseX > 327) && (mouseY < 550) && (mouseY > 500) && (mousePressed) && game.getMode1() == "1 PLAYER") {
                game.setDiff("HARD");
                //  System.out.print(game.getDiff());
            }
            if ((mouseX < 777) && (mouseX > 577) && (mouseY < 550) && (mouseY > 500) && (mousePressed) && game.getMode1() == "1 PLAYER") {
                game.setDiff("INFINITY");
                //  System.out.print(game.getDiff());
            }

            if (game.getMode1() == "2 PLAYERS") {
                game.setPlayerMode(2);
                background(im_menu);
                board.create_case(77, 550, 277, 500, 255, 255, 255);
                board.create_text("EASY", 25, 150, 535, 0, 0, 0);
                board.image(im_car_right, 95, 508, 38, 38);
                board.image(im_car_left, 220, 508, 38, 38);
                board.create_case(327, 550, 527, 500, 255, 255, 255);
                board.create_text("HARD", 25, 395, 535, 0, 0, 0);
                board.image(im_car_right, 350, 508, 38, 38);
                board.image(im_trunk, 465, 517, 38, 20);
                board.create_case(670, 700, 800, 660, 255, 255, 255);
                board.create_text("BACK", 25, 720, 690, 0, 0, 0);
                board.image(im_back_arrow, 680, 662, 38, 38);
            }

// Après avoir choisi le mode 2 joueurs, on choisit le niveau de difficulté
            if ((mouseX < 277) && (mouseX > 77) && (mouseY < 550) && (mouseY > 500) && (mousePressed) && game.getMode1() == "2 PLAYERS") {
                game.setDiff("EASY");
                //   System.out.print(game.getDiff());
            }
            if ((mouseX < 527) && (mouseX > 327) && (mouseY < 550) && (mouseY > 500) && (mousePressed) && game.getMode1() == "2 PLAYERS") {
                game.setDiff("HARD");
                //   System.out.print(game.getDiff());
            }

            // LE BOUTON RETOUR
            if ((mouseX < 800) && (mouseX > 670) && (mouseY < 700) && (mouseY > 660) && (mousePressed) && game.getMode1() != null) {
                game.setMode1(null);

            }
            // ON AFFICHE LES DIFFICULTES POUR LES LEADERBOARDS
            if (game.getMode1() == "LEADERBOARD") {
                background(im_menu);
                board.create_case(77, 300, 277, 250, 255, 255, 255);
                board.create_text("EASY", 25, 150 , 285, 0, 0, 0);
                board.image(im_car_right,95,258, 38, 38);
                board.image(im_car_left,220,258, 38, 38);
                board.create_case(77, 400, 277, 350, 255, 255, 255);
                board.create_text("HARD", 25, 150, 385, 0, 0, 0);
                board.image(im_car_right,95,358, 38, 38);
                board.image(im_trunk,220,365, 38, 20);
                board.create_case(77,500 , 277, 450, 255, 255, 255);
                board.create_text("INFINITY", 25, 130, 485, 0, 0, 0);
                board.image(im_rb_pepe_right,90,458, 38, 38);
                board.image(im_rb_pepe_left,225,458, 38, 38);
                board.create_case(670, 700, 800, 660, 255, 255, 255);
                board.create_text("BACK", 25, 720 , 690, 0, 0, 0);
                board.image(im_back_arrow,680,662, 38, 38);
            }

            if ((mouseX < 277) && (mouseX > 77) && (mouseY < 300) && (mouseY > 250) && (mousePressed) && game.getMode1() == "LEADERBOARD") {
                game.setMode2("EASY");
                game.setMode3("diff");

            }
            if ((mouseX < 277) && (mouseX > 77) && (mouseY < 400) && (mouseY > 350) && (mousePressed) && game.getMode1() == "LEADERBOARD" ) {
                game.setMode2("HARD");
                game.setMode3("diff");
            }
            if ((mouseX < 277) && (mouseX > 77) && (mouseY < 500) && (mouseY > 450) && (mousePressed) && game.getMode1() == "LEADERBOARD") {
                game.setMode2("INFINITY");
                game.setMode3("diff");

            }
            // ON AFFICHE LE LEADERBOARD CORRESPONDANT A LA DIFFICULTE SELECTIONNE
            // LEADERBOARD EASY
            if (game.getMode2() == "EASY") {
                background(im_menu);
                board.image(im_leaderboard_easy, 180, 70, 500, 600);
                board.create_case(670, 700, 800, 660, 255, 255, 255);
                board.create_text("BACK", 25, 720 , 690, 0, 0, 0);
                board.image(im_back_arrow,680,662, 38, 38);
                scores = game.show_leaderboard(record);
                int x = 415;
                int y = 265;
                for (String score: scores) {
                    board.create_text(score.split(" ")[1], 25,x, y,0,0,0);
                    y += 35;
                }
            }
            // LEADERBOARD HARD
            if (game.getMode2() == "HARD") {
                background(im_menu);
                board.image(im_leaderboard_hard, 180, 70, 500, 600);
                board.create_case(670, 700, 800, 660, 255, 255, 255);
                board.create_text("BACK", 25, 720 , 690, 0, 0, 0);
                board.image(im_back_arrow,680,662, 38, 38);
                scores = game.show_leaderboard(record_hard);
                int x = 415;
                int y = 265;
                for (String score: scores) {
                    board.create_text(score.split(" ")[1], 25,x, y,0,0,0);
                    y += 35;
                }
            }
            // LEADERBOARD INFINITY
            if (game.getMode2() == "INFINITY") {
                background(im_menu);
                board.image(im_leaderboard_infinity, 180, 70, 500, 600);
                board.create_case(670, 700, 800, 660, 255, 255, 255);
                board.create_text("BACK", 25, 720 , 690, 0, 0, 0);
                board.image(im_back_arrow,680,662, 38, 38);
                scores = game.show_leaderboard(record_infinity);
                int x = 415;
                int y = 265;
                for (String score: scores) {
                    board.create_text(score.split(" ")[1], 25,x, y,0,0,0);
                    y += 35;
                }
            }

            // LE RETOUR DU BOUTON RETOUR
            if ((mouseX < 800) && (mouseX > 670) && (mouseY < 700) && (mouseY > 660) && (mousePressed) && game.getMode3() == "diff") {
                game.setMode3(null);
                game.setMode2(null);
            }

            // INITIALISER LE JEU UNE FOIS QUE L'ON A LE PLAYERMODE ET LE NIVEAU DE DIFFICULTE
            if (game.getPlayerMode()==2){
                frog1 = game.set_Frog2P1();
                frog2 = game.set_Frog2P2();
            }
            else {
                frog2 = game.set_Frog();
            }

            if (game.getDiff() == "HARD") {
                cars = game.allCars(separate);
                trunks = game.allTrunks(separate + 2, ranges - 2);
            } else if (game.getDiff() == "EASY"){
                cars = game.allCars(ranges - 2);
            } else if (game.getDiff() == "INFINITY"){
                cars = game.allCars(ranges);
            }


        } else {   // LE JEU EN LUI-MEME

            background(0);
            t_i = (millis() - t1) / 1000;

            if (game.getDiff() == "HARD") {  // AFFICHE LA DEMARCATION ENTRE VOITURES ET TRONCS EN MODE HARD
                board.create_case(0, (separate - 1) * grid, game.getGame_width(), game.getGame_height() - grid, 20, 20, 30);
                board.create_case(0, (separate - 2) * grid, game.getGame_width(), grid, 0, 50, 100);
                board.create_case(0, grid, game.getGame_width(), 0, 0, 200, 0);
            } else if (game.getDiff() == "EASY") {  // AFFICHE SEULEMENT LE DEPART ET L'ARRIVEE EN MODE EASY
                board.create_case(0, - grid, game.getGame_width(), game.getGame_height() - grid, 20, 20, 30);
                board.create_case(0, grid, game.getGame_width(), 0, 0, 200, 0);
            }


            if (game.getPlayerMode() == 2) {  // 2 JOUEURS
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

                if (game.getDiff() == "HARD") {  // 2 JOUEURS EN MODE HARD, ON RAJOUTE LES TRONCS
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
                }
                board.show_frog(frog1, im_frog);
                board.show_frog(frog2, im_frog2);
                game.deal_state_frog2P1(frog1);
                game.deal_state_frog2P2(frog2);


            } else {  // 1 JOUEUR
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
                if (game.getDiff() == "HARD") {  // 1 JOUEUR EN MODE HARD, ON RAJOUTE LES TRONCS
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
                }

                if (game.getDiff() == "INFINITY") {  // 1 JOUEUR EN MODE INFINITY, ON AUGMENTE LA VITESSE DES VOITURES TOUTES LES 50 RANGEES
                    score_inf = frog2.getRange();
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
                }

                board.show_frog(frog2, im_frog2);
                game.deal_state_frog(frog2);


            }

            if (game.getDiff() == "INFINITY"){
                board.create_text("Score : "+ score_inf, 20, grid/2, grid/2, 255, 255, 255);
                if (frog2.isGAMEOVER()) {
                    remark = endless_treatment(record_infinity,score_inf);
                    board.create_text("Your score is " + score_inf, 32, game.getGame_width() / 2 - 6*grid, game.getGame_height() / 2, 0, 255, 0);
                    board.create_text(remark, 32, game.getGame_width() / 2 -  6*grid, game.getGame_height() / 2 + grid, 0, 255, 0);
                    processing.stop();
                }
            } else {
                board.create_text(t_i + "s", 20, grid / 2, grid / 2, 0, 0, 0);

                if (game.getGameState()) {
                    t2 = millis();
                    t_fin = (t2 - t1) / 1000;

                    board.create_text("Congratulations ! You beat Frogger in " + t_fin + "s.", 32, game.getGame_width() / 2 - 6 * grid, game.getGame_height() / 2, 255, 255, 255);
                    if (game.getDiff() == "EASY"){
                        remark = record_treatment(record, t_fin);
                    } else if (game.getDiff() == "HARD"){
                        remark = record_treatment(record_hard, t_fin);
                    }
                    board.create_text(remark, 32, game.getGame_width() / 2 - 6 * grid, game.getGame_height() / 2 + grid, 255, 255, 0);
                    processing.stop();

                }
            }


        }
    }


    @Override
    public void keyPressed() {
        if (keyCode == UP){
            if (game.getDiff() == "INFINITY"){
                game.move_allCars(cars, grid);
            } else {
                frog2.move(0,-grid);
            }
            frog2.setRange(frog2.getRange()+1);
            //System.out.println("bottom :" + frog.getBottom());

        }
        else if ((keyCode == DOWN) && (game.getDiff() != "INFINITY")) {
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

