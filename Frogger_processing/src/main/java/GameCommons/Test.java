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

public class Test extends PApplet {

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
    PImage im_frog;
    PImage im_frog2;
    PImage im_menu;
    PImage im_car;
    PImage im_rev;
    PImage im_log;
    PImage im_turtle;
    int PlayerMode;
    Path record;
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
        game = new Game(grid, ranges, columns, PlayerMode);
        size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {
        im_menu = loadImage("src\\main\\java\\Images\\Menu.png");
        im_menu.resize(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void draw() {

        if (game.getMode1() == null) {
            background(im_menu);
            board.create_case(game.getGame_width() / 2, game.getGame_height() / 2, game.getGame_width() / 2 + 3 * grid, game.getGame_height() / 2 - grid, 255, 255, 255);
            board.create_text("1 PLAYER", 32, game.getGame_width() / 2, game.getGame_height() / 2, 0, 0, 0);
            board.create_case(game.getGame_width() / 2, game.getGame_height() / 2 + 4 * grid, game.getGame_width() / 2 + 3 * grid, game.getGame_height() / 2 + 3 * grid, 255, 255, 255);
            board.create_text("2 PLAYERS", 32, game.getGame_width() / 2, game.getGame_height() / 2 + 4 * grid, 0, 0, 0);
        }
// ACTION LORSQUE L'ON CLIQUE SUR "1 PLAYER" : ON AFICHE LE MEME MENU MAIS AVEC LES BOUTONS "EASY" et "HARD"

        if ((mouseX < game.getGame_width() / 2 + 3 * grid) && (mouseX > game.getGame_width() / 2) && (mouseY < game.getGame_height() / 2) && (mouseY > game.getGame_height() / 2 - grid) && (mousePressed)) {
            game.setMode1("1 PLAYER");

        }
        if ((mouseX < game.getGame_width() / 2 + 3 * grid) && (mouseX > game.getGame_width() / 2) && (mouseY < game.getGame_height() / 2 + 4 * grid) && (mouseY > game.getGame_height() / 2 + 3 * grid) && (mousePressed)) {
            game.setMode1("2 PLAYERS");

        }
        if (game.getMode1() == "1 PLAYER") {
            background(0);
            board.create_text("1 PLAYER ", 64, game.getGame_width() / 2, game.getGame_height() / 2, 255, 255, 255);
        }

        if (game.getMode1() == "2 PLAYERS") {
            background(0);
            board.create_text("2 PLAYERS", 64, game.getGame_width() / 2, game.getGame_height() / 2, 255, 255, 255);

            }
        }
    }



