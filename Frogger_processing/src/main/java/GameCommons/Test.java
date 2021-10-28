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

//import processing.sound.*;

public class Test extends PApplet {

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
    PImage im_frog;
    PImage im_frog2;
    PImage im_menu;
    PImage im_car;
    PImage im_rev;
    PImage im_log;
    PImage im_turtle;
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
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PApplet.main(new String[]{Test.class.getName()});
    }

    @Override
    public void settings() {
        processing = this;
        ranges = 16; // de préférance pair
        columns = 19;
        grid = 45;
        separate = ranges / 2;
        board = new Element(processing);
        game = new Game(grid, ranges, columns, PlayerMode, Diff);
        size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {
        record = Paths.get("src/main/java/GameCommons/Record_easy");
        im_menu = loadImage("src/main/java/Images/Menu.png");
        im_menu.resize(game.getGame_width(), game.getGame_height());
        im_cursor = loadImage("src/main/java/Images/cursor.png");
        im_frog2 = loadImage("src/main/java/Images/frog2.png");
        im_frog = loadImage("src/main/java/Images/frog.png");
        im_car_right = loadImage("src/main/java/Images/car_right.png");
        im_car_left = loadImage("src/main/java/Images/car_left.png");
        im_trunk = loadImage("src/main/java/Images/trunk.png");
        im_rb_pepe_right = loadImage("src/main/java/Images/rb_pepe_right.jpg");
        im_rb_pepe_left = loadImage("src/main/java/Images/rb_pepe_left.png");
        im_cup = loadImage("src/main/java/Images/cup.png");
        im_back_arrow = loadImage("src/main/java/Images/back_arrow.png");
        im_leaderboard_easy = loadImage("src/main/java/Images/leaderboard_easy.png");
        im_leaderboard_hard = loadImage("src/main/java/Images/leaderboard_hard.png");
        im_leaderboard_infinity = loadImage("src/main/java/Images/leaderboard_infinity.png");
    }

    @Override
    public void draw() {

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
            board.create_case(77, 600, 277, 550, 255, 255, 255);
            board.create_text("EASY", 25, 150 , 585, 0, 0, 0);
            board.image(im_car_right,95,558, 38, 38);
            board.image(im_car_left,220,558, 38, 38);
            board.create_case(327, 600, 527, 550, 255, 255, 255);
            board.create_text("HARD", 25, 395, 585, 0, 0, 0);
            board.image(im_car_right,350,558, 38, 38);
            board.image(im_trunk,465,567, 38, 20);
            board.create_case(577,600 , 777, 550, 255, 255, 255);
            board.create_text("INFINITY", 25, 630, 585, 0, 0, 0);
            board.image(im_rb_pepe_right,585,558, 38, 38);
            board.image(im_rb_pepe_left,730,558, 38, 38);
            board.create_case(670, 700, 800, 660, 255, 255, 255);
            board.create_text("BACK", 25, 720 , 690, 0, 0, 0);
            board.image(im_back_arrow,680,662, 38, 38);
        }

        if (game.getMode1() == "2 PLAYERS") {
            game.setPlayerMode(2);
            background(im_menu);
            board.create_case(77, 600, 277, 550, 255, 255, 255);
            board.create_text("EASY", 25, 150 , 585, 0, 0, 0);
            board.image(im_car_right,95,558, 38, 38);
            board.image(im_car_left,220,558, 38, 38);
            board.create_case(327, 600, 527, 550, 255, 255, 255);
            board.create_text("HARD", 25, 395, 585, 0, 0, 0);
            board.image(im_car_right,350,558, 38, 38);
            board.image(im_trunk,465,567, 38, 20);
            board.create_case(577,600 , 777, 550, 255, 255, 255);
            board.create_text("INFINITY", 25, 630, 585, 0, 0, 0);
            board.image(im_rb_pepe_right,585,558, 38, 38);
            board.image(im_rb_pepe_left,730,558, 38, 38);
            board.create_case(670, 700, 800, 660, 255, 255, 255);
            board.create_text("BACK", 25, 720 , 690, 0, 0, 0);
            board.image(im_back_arrow,680,662, 38, 38);

            }
        // LE BOUTON RETOUR
        if ((mouseX < 800) && (mouseX > 670) && (mouseY < 700) && (mouseY > 660) && (mousePressed) && game.getMode1() != null) {
//            background(im_menu);
//            board.create_case(77, 600, 277, 550, 255, 255, 255);
//            board.image(im_frog,82,558, 40, 40);
//            board.create_text("1 PLAYER", 25, 130 , 585, 0, 0, 0);
//            board.create_case(327, 600, 527, 550, 255, 255, 255);
//            board.image(im_frog,332,558, 38, 38);
//            board.image(im_frog2,485,558, 38, 38);
//            board.create_text("2 PLAYERS", 25, 370, 585, 0, 0, 0);
//            board.create_case(577,600 , 777, 550, 255, 255, 255);
//            board.create_text("LEADERBOARD", 25, 615, 585, 0, 0, 0);
//            board.image(im_cup,580,558, 38, 38);
            game.setMode1(null);

        }
        // ON AFFICHE LES DIFFICULTE POUR LES LEADERBOARD
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
            game.setDiff("EASY");
            game.setMode3("diff");

        }
        if ((mouseX < 277) && (mouseX > 77) && (mouseY < 400) && (mouseY > 350) && (mousePressed) && game.getMode1() == "LEADERBOARD" ) {
            game.setDiff("HARD");
            game.setMode3("diff");
        }
        if ((mouseX < 277) && (mouseX > 77) && (mouseY < 500) && (mouseY > 450) && (mousePressed) && game.getMode1() == "LEADERBOARD") {
            game.setDiff("INFINITY");
            game.setMode3("diff");

        }
        // ON AFFICHE LE LEADERBOARD CORRESPONDANT A LA DIFFICULTE SELECTIONNE
        // LEADERBOARD EASY
        if (game.getDiff() == "EASY") {
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
        if (game.getDiff() == "HARD") {
            background(im_menu);
            board.image(im_leaderboard_hard, 180, 70, 500, 600);
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
        // LEADERBOARD INFINITY
        if (game.getDiff() == "INFINITY") {
            background(im_menu);
            board.image(im_leaderboard_infinity, 180, 70, 500, 600);
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
        // LE RETOUR DU BOUTON RETOUR
        if ((mouseX < 800) && (mouseX > 670) && (mouseY < 700) && (mouseY > 660) && (mousePressed) && game.getMode3() == "diff") {
//            background(im_menu);
//            board.create_case(77, 300, 277, 250, 255, 255, 255);
//            board.create_text("EASY", 25, 150 , 285, 0, 0, 0);
//            board.image(im_car_right,95,258, 38, 38);
//            board.image(im_car_left,220,258, 38, 38);
//            board.create_case(77, 400, 277, 350, 255, 255, 255);
//            board.create_text("HARD", 25, 150, 385, 0, 0, 0);
//            board.image(im_car_right,95,358, 38, 38);
//            board.image(im_trunk,220,365, 38, 20);
//            board.create_case(77,500 , 277, 450, 255, 255, 255);
//            board.create_text("INFINITY", 25, 130, 485, 0, 0, 0);
//            board.image(im_rb_pepe_right,90,458, 38, 38);
//            board.image(im_rb_pepe_left,225,458, 38, 38);
//            board.create_case(670, 700, 800, 660, 255, 255, 255);
//            board.create_text("BACK", 25, 720 , 690, 0, 0, 0);
//            board.image(im_back_arrow,680,662, 38, 38);
            game.setMode3(null);
            game.setDiff(null);
        }
    }}



