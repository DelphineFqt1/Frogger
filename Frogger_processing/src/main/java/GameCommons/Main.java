package GameCommons;

import GraphicalElements.Button;
import MovingElements.Frog;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import processing.core.PApplet;
import GraphicalElements.Element;
import processing.core.PImage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Util.Direction;
import static Util.UtilClass.endless_treatment;
import static Util.UtilClass.record_treatment;
import static Util.UtilClass.get_leaderboard_data;

import ddf.minim.*;

/**
 * Main qui sert a lancer le jeu, avec des parametres specifiables dans le settings
 */
public class Main extends PApplet {

    Frog frog1;
    Frog frog2;
    Button button1P;
    Button button2P;
    Button buttonLead;
    Button buttonEasy;
    Button buttonHard;
    Button buttonInf;
    Button buttonBack;
    Button buttonLE;
    Button buttonLH;
    Button buttonLI;
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
    float t_i;
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
    Path record;
    Path record_hard;
    Path record_infinity;
    String remark = null;
    int count50 = 50;
    int score_inf;
    Minim minim;
    String music_menu;
    String music_bouton;
    String music_infinity;
    String music_hard;
    String music_easy;
    String music_jump;
    String music_victory_easy;
    String music_victory_hard;
    String music_collision;
    String music_defeat_infinity;
    AudioPlayer player_menu;
    AudioPlayer player_bouton;
    AudioPlayer player_infinity;
    AudioPlayer player_hard;
    AudioPlayer player_easy;
    AudioPlayer player_jump;
    AudioPlayer player_victory_easy;
    AudioPlayer player_victory_hard;
    AudioPlayer player_collision;
    AudioPlayer player_defeat_infinity;

/**
 * Lance l'application
 */
    public static void main(String[] args) {
        PApplet.main(new String[]{Main.class.getName()});
    }

    /**
     * Permet d'entrer les parametres utiles au deroulement du jeu
     */
    @Override
    public void settings() {

        ranges = 16; // de preference pair
        columns = 19;
        grid = 45;
        separate = ranges/2 ;
        board = new Element(this);
        minim = new Minim(this);
        game = new Game(grid, ranges, columns);
        board.size(game.getGame_width(), game.getGame_height());
    }

    /**
     * Permet de declarer les objets utiles au deroulement du jeu
     */
    @Override
    public void setup() {
        String im = "src/main/java/Resources/Images";
        String aud = "src/main/java/Resources/Audio";
        String rec = "src/main/java/Resources/Records";
        im_frog2 = loadImage(im + "/frog2.png");
        im_frog = loadImage(im + "/frog.png");
        im_menu = loadImage(im + "/Menu.png");
        record = Paths.get(rec + "/Record_easy");
        record_hard = Paths.get(rec + "/Record_hard");
        record_infinity = Paths.get(rec + "/Record_endless");
        im_car_right = loadImage(im + "/car_right.png");
        im_car_left = loadImage(im + "/car_left.png");
        im_trunk = loadImage(im + "/trunk.png");
        im_cursor = loadImage(im + "/cursor.png");
        im_rb_pepe_right = loadImage(im + "/rb_pepe_right.png");
        im_rb_pepe_left = loadImage(im + "/rb_pepe_left.png");
        im_cup = loadImage(im + "/cup.png");
        im_back_arrow = loadImage(im + "/back_arrow.png");
        im_leaderboard_easy = loadImage(im + "/leaderboard_easy.png");
        im_leaderboard_hard = loadImage(im + "/leaderboard_hard.png");
        im_leaderboard_infinity = loadImage(im + "/leaderboard_infinity.png");

        music_menu = aud + "/Death by glamour.wav";
        music_bouton = aud + "/Button.wav";
        music_infinity = aud + "/Beyond the heart.wav";
        music_hard = aud + "/Reach for the summit.wav";
        music_easy = aud + "/Area 1 Demo.wav";
        music_jump = aud + "/Jump.wav";
        music_victory_easy = aud + "/Victory_easy.wav";
        music_victory_hard = aud + "/Victory_hard.wav";
        music_collision = aud + "/Collision.wav";
        music_defeat_infinity = aud + "/Defeat.wav";
        player_menu = minim.loadFile(music_menu);
        player_menu.setGain(0);
        player_menu.loop();

        button1P = new Button(this, 77, 600, 277, 550, "1 PLAYER" );
        button2P = new Button(this, 315, 600, 547, 550, "2 PLAYERS" );
        buttonLead = new Button(this, 577,600 , 810, 550, "LEADERBOARD" );
        buttonEasy = new Button(this, 77, 550, 277, 500, "  EASY" );
        buttonHard = new Button(this, 327, 550, 527, 500, "  HARD" );
        buttonInf = new Button(this, 577, 550, 790, 500, "INFINITE" );
        buttonBack = new Button(this, 670, 700, 800, 660, "  BACK" );
        buttonLE = new Button(this, 77, 300, 277, 250, "EASY");
        buttonLH = new Button(this, 77, 400, 277, 350, "HARD");
        buttonLI = new Button(this,77,500 , 277, 450,"INFINITE");
    }

    /**
     * Deroule le jeu
     */
    @Override
    public void draw() {
        if (game.getRestart()==1){
            player_menu = minim.loadFile(music_menu);
            player_menu.setGain(0);
            player_menu.loop();
            game.setRestart(0);
        }

        if (game.getDiff() == null) {   // ECRAN D'ACCUEIL + CHOIX DU PLAYERMODE ET DU NIVEAU DE DIFFICULTE
            t1 = millis();
            if (game.getPlayerMode() == null) {
                board.background_im(im_menu, game.getGame_width(), game.getGame_height());
                button1P.show();
                board.show_image(im_frog,button1P.getLeft()+5,button1P.getTop()-42, 40, 40);

                button2P.show();
                board.show_image(im_frog,button2P.getLeft()+5,button2P.getTop()-42, 40, 40);
                board.show_image(im_frog2,button2P.getRight()-40,button2P.getTop()-42, 38, 38);

                buttonLead.show();
                board.show_image(im_cup,buttonLead.getLeft()+10,buttonLead.getTop()-45, 40, 40);
            }

// ACTION LORSQUE L'ON CLIQUE SUR "1 PLAYER" : ON AFFICHE LE MEME MENU MAIS AVEC LES BOUTONS "EASY" et "HARD"
            if (button1P.click_event() && game.getPlayerMode()==null) {
                game.setPlayerMode("1 PLAYER");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
            }
            if (button2P.click_event() && game.getPlayerMode()==null) {
                game.setPlayerMode("2 PLAYERS");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
            }
            if (buttonLead.click_event() && game.getPlayerMode() == null) {
                game.setPlayerMode("LEADERBOARD");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
            }

            // ON AFFICHE LES BOUTONS DU MODE DE JEU (ROUTE, ROUTE + RIVIERE, INFINI)
            if (game.getPlayerMode()== "1 PLAYER") {
                board.background_im(im_menu, game.getGame_width(), game.getGame_height());
                
                buttonEasy.show();
                board.show_image(im_car_right, buttonEasy.getLeft()+10, buttonEasy.getTop()-45, 38, 38);
                board.show_image(im_car_left, buttonEasy.getLeft()+150, buttonEasy.getTop()-45, 38, 38);

                buttonHard.show();
                board.show_image(im_car_right, buttonHard.getLeft()+10, buttonHard.getTop()-45, 38, 38);
                board.show_image(im_trunk, buttonHard.getLeft()+150, buttonHard.getTop()-35, 38, 20);

                buttonInf.show();
                board.show_image(im_rb_pepe_right, buttonInf.getLeft()+10, buttonInf.getTop()-45, 38, 38);
                board.show_image(im_rb_pepe_left, buttonInf.getRight()-40, buttonInf.getTop()-45, 38, 38);

                buttonBack.show();
                board.show_image(im_back_arrow, buttonBack.getLeft(), buttonBack.getTop()-40, 36, 36);
            }

// Apres avoir choisi le mode 1 joueur, on choisit le niveau de difficulte
            if (buttonEasy.click_event() && game.getPlayerMode() == "1 PLAYER") {
                game.setDiff("EASY");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
                player_menu.close();
                player_easy = minim.loadFile(music_easy);
                player_easy.setGain(-8);
                player_easy.loop();

            }
            if (buttonHard.click_event() && game.getPlayerMode() == "1 PLAYER") {
                game.setDiff("HARD");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
                player_menu.close();
                player_hard = minim.loadFile(music_hard);
                player_hard.setGain(-10);
                player_hard.loop();
            }
            if (buttonInf.click_event() && game.getPlayerMode() == "1 PLAYER") {
                game.setDiff("INFINITE");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
                player_menu.close();
                player_infinity = minim.loadFile(music_infinity);
                player_infinity.setGain(-5);
                player_infinity.loop();
            }

            if (game.getPlayerMode() == "2 PLAYERS") {
                board.background_im(im_menu, game.getGame_width(), game.getGame_height());

                buttonEasy.show();
                board.show_image(im_car_right, buttonEasy.getLeft()+10, buttonEasy.getTop()-45, 38, 38);
                board.show_image(im_car_left, buttonEasy.getLeft()+150, buttonEasy.getTop()-45, 38, 38);

                buttonHard.show();
                board.show_image(im_car_right, buttonHard.getLeft()+10, buttonHard.getTop()-45, 38, 38);
                board.show_image(im_trunk, buttonHard.getLeft()+150, buttonHard.getTop()-35, 38, 20);

                buttonBack.show();
                board.show_image(im_back_arrow, buttonBack.getLeft(), buttonBack.getTop()-40, 36, 36);
            }

// Apres avoir choisi le mode 2 joueurs, on choisit le niveau de difficulte
            if (buttonEasy.click_event() && game.getPlayerMode() == "2 PLAYERS") {
                game.setDiff("EASY");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
                player_menu.close();
                player_easy = minim.loadFile(music_easy);
                player_easy.setGain(-8);
                player_easy.loop();
            }
            if (buttonHard.click_event() && game.getPlayerMode() == "2 PLAYERS") {
                game.setDiff("HARD");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
                player_menu.close();
                player_hard = minim.loadFile(music_hard);
                player_hard.setGain(-10);
                player_hard.loop();
            }

            // LE BOUTON RETOUR
            if (buttonBack.click_event() && game.getPlayerMode() != null) {
                game.setPlayerMode(null);
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();

            }
            // ON AFFICHE LES DIFFICULTES POUR LES LEADERBOARDS
            if (game.getPlayerMode() == "LEADERBOARD") {
                board.background_im(im_menu, game.getGame_width(), game.getGame_height());

                buttonLE.show();
                board.show_image(im_car_right,buttonLE.getLeft(),buttonLE.getTop()-45, 38, 38);
                board.show_image(im_car_left,buttonLE.getRight()-40,buttonLE.getTop()-45, 38, 38);

                buttonLH.show();
                board.show_image(im_car_right,buttonLH.getLeft(),buttonLH.getTop()-45, 38, 38);
                board.show_image(im_trunk,buttonLH.getRight()-40,buttonLH.getTop()-35, 38, 20);

                buttonLI.show();
                board.show_image(im_rb_pepe_right,buttonLI.getLeft(),buttonLI.getTop()-40, 38, 38);
                board.show_image(im_rb_pepe_left,buttonLI.getRight()-40,buttonLI.getTop()-40, 38, 38);

                buttonBack.show();
                board.show_image(im_back_arrow, buttonBack.getLeft(), buttonBack.getTop()-40, 36, 36);
            }

            if (buttonLE.click_event() && game.getPlayerMode() == "LEADERBOARD") {
                game.setLeaderboard("EASY");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
            }
            if (buttonLH.click_event() && game.getPlayerMode() == "LEADERBOARD" ) {
                game.setLeaderboard("HARD");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();
            }
            if (buttonLI.click_event() && game.getPlayerMode() == "LEADERBOARD") {
                game.setLeaderboard("INFINITE");
                player_bouton = minim.loadFile(music_bouton);
                player_bouton.play();

            }
            // ON AFFICHE LE LEADERBOARD CORRESPONDANT A LA DIFFICULTE SELECTIONNEE
            // LEADERBOARD EASY
            if (game.getLeaderboard() == "EASY") {
                board.background_im(im_menu, game.getGame_width(), game.getGame_height());
                board.show_image(im_leaderboard_easy, 180, 70, 500, 600);
                buttonBack.show();
                board.show_image(im_back_arrow, buttonBack.getLeft(), buttonBack.getTop()-40, 36, 36);
                try{
                scores = get_leaderboard_data(record);
                int x = 415;
                int y = 265;
                for (String score: scores) {
                    board.create_text(score.split(" ")[1], 25,x, y,0,0,0);
                    y += 35;
                }}
                catch(IOException e){
                    board.create_case(480, 70, 840, 0, 0,0,0);
                    board.create_text("Warning : \nPath specified for score reading not found", 20, 480, 30, 255,0,0);
                }
            }
            // LEADERBOARD HARD
            if (game.getLeaderboard() == "HARD") {
                board.background_im(im_menu, game.getGame_width(), game.getGame_height());
                board.show_image(im_leaderboard_hard, 180, 70, 500, 600);
                buttonBack.show();
                board.show_image(im_back_arrow, buttonBack.getLeft(), buttonBack.getTop()-40, 36, 36);
                try{
                scores = get_leaderboard_data(record_hard);
                int x = 415;
                int y = 265;
                for (String score: scores) {
                    board.create_text(score.split(" ")[1], 25,x, y,0,0,0);
                    y += 35;
                }
                }catch(IOException e){
                board.create_case(480, 70, 840, 0, 0,0,0);
                board.create_text("Warning : \nPath specified for score reading not found", 20, 480, 30, 255,0,0);
            }
            }
            // LEADERBOARD INFINITY
            if (game.getLeaderboard() == "INFINITE") {
                board.background_im(im_menu, game.getGame_width(), game.getGame_height());
                board.show_image(im_leaderboard_infinity, 180, 70, 500, 600);
                buttonBack.show();
                board.show_image(im_back_arrow, buttonBack.getLeft(), buttonBack.getTop()-40, 36, 36);
                try{
                scores = get_leaderboard_data(record_infinity);
                int x = 415;
                int y = 265;
                for (String score: scores) {
                    board.create_text(score.split(" ")[1], 25,x, y,0,0,0);
                    y += 35;
                }}
                catch(IOException e){
                    board.create_case(480, 70, 840, 0, 0,0,0);
                    board.create_text("Warning : \nPath specified for score reading not found", 20, 480, 30, 255,0,0);
                }
            }

            // LE RETOUR DU BOUTON RETOUR
            if (buttonBack.click_event() && game.getLeaderboard()!=null) {
                game.setLeaderboard(null);
                }

            // INITIALISER LE JEU UNE FOIS QUE L'ON A LE PLAYERMODE ET LE NIVEAU DE DIFFICULTE
            frog1 = game.setFrog(1);
            frog2 = game.setFrog(2);

            if (game.getDiff() == "HARD") {
                cars = game.allCars(separate);
                trunks = game.allTrunks(separate + 2, ranges - 2);
            } else if (game.getDiff() == "EASY"){
                cars = game.allCars(ranges - 2);
            } else if (game.getDiff() == "INFINITE"){
                cars = game.allCars(ranges);
            }
        }
        else if (game.getDiff()!=null){   // LE JEU EN LUI-MEME EST LANCE

            // On recupere le temps ou le joueur lance une partie (une seule fois)
            t_i = (millis()-t1)/1000;
            board.background(25);
            if (game.getDiff() == "HARD") {  // AFFICHE LA LIGNE D'EAU, LA DEMARCATION AVEC L'AUTOROUTE ET L'ARRIVEE EN MODE HARD
                board.create_case(0, (separate - 1) * game.getGrid(), game.getGame_width(), game.getGame_height() - game.getGrid(), 20, 20, 30);
                board.create_case(0, (separate - 2) * grid, game.getGame_width(), grid, 0, 50, 100);
                board.create_case(0, grid, game.getGame_width(), 0, 0, 200, 0);
            } else if (game.getDiff() == "EASY") {  // AFFICHE SEULEMENT L'ARRIVEE EN MODE EASY
                board.create_case(0, grid, game.getGame_width(), 0, 0, 200, 0);
            }

            if (game.getPlayerMode() == "2 PLAYERS") {  // 2 JOUEURS
                for (ArrayList<Car> range_i : cars) {
                    for (Car car : range_i) {
                        car.move(car.getSpeed(), 0);
                        if (car.getSpeed() > 0) {
                            board.show_car(im_car_right, car);
                        }
                        else {
                            board.show_car(im_car_left, car);
                        }
                        if (frog1.intersect(car)) {
                            frog1.setCar_intersection(true);
                            player_collision = minim.loadFile(music_collision);
                            player_collision.setGain(-5);
                            player_collision.play();
                        }
                        if (frog2.intersect(car)) {
                            frog2.setCar_intersection(true);
                            player_collision = minim.loadFile(music_collision);
                            player_collision.setGain(-5);
                            player_collision.play();
                        }
                    }
                }

                if (game.getDiff() == "HARD") {  // 2 JOUEURS EN MODE HARD, ON RAJOUTE LES TRONCS
                    int count_inter = 0;
                    int count2 = 0;
                    for (ArrayList<Trunk> range_i : trunks) {
                        for (Trunk trunk : range_i) {
                            trunk.move(trunk.getSpeed(), 0);
                            board.show_trunk(im_trunk, trunk);
                            if (frog1.intersect(trunk)) {
                                count_inter++;
                                if (count_inter == 1) {
                                    frog1.setLeft(frog1.getLeft() + trunk.getSpeed());
                                    frog1.setRight(frog1.getRight() + trunk.getSpeed());
                                }
                            }
                            if (frog2.intersect(trunk)) {
                                count2++;
                                if (count2 == 1) {
                                    frog2.setLeft(frog2.getLeft() + trunk.getSpeed());
                                    frog2.setRight(frog2.getRight() + trunk.getSpeed());
                                }
                            }
                        }
                    }

                    if (frog1.getRange() > (separate + 1) && frog1.getRange() < ranges - 1 && count_inter == 0) {
                        frog1.setTrunk_intersection(false);
                        player_collision = minim.loadFile(music_collision);
                        player_collision.setGain(-5);
                        player_collision.play();
                    }
                    if (frog2.getRange() > (separate + 1) && frog2.getRange() < ranges - 1 && count2 == 0) {
                        frog2.setTrunk_intersection(false);
                        player_collision = minim.loadFile(music_collision);
                        player_collision.setGain(-5);
                        player_collision.play();
                    }
                }
                board.show_frog(im_frog, frog1);
                board.show_frog(im_frog2, frog2);
                game.stateFrog(frog1, 1);
                game.stateFrog(frog2, 2);


            } else if (game.getPlayerMode()=="1 PLAYER"){  // 1 JOUEUR
                for (ArrayList<Car> range_i : cars) {
                    for (Car car : range_i) {
                        car.move(car.getSpeed(), 0);
                        if (car.getSpeed() > 0) {
                            board.show_car(im_car_right, car);
                        }
                        else {
                            board.show_car(im_car_left, car);
                        }
                        if (frog1.intersect(car)) {
                            frog1.setCar_intersection(true);
                            player_collision = minim.loadFile(music_collision);
                            player_collision.setGain(-5);
                            player_collision.play();
                        }
                    }
                }
                if (game.getDiff() == "HARD") {  // 1 JOUEUR EN MODE HARD, ON RAJOUTE LES TRONCS
                    int count2 = 0;
                    for (ArrayList<Trunk> range_i : trunks) {
                        for (Trunk trunk : range_i) {
                            trunk.move(trunk.getSpeed(), 0);
                            board.show_trunk(im_trunk, trunk);
                            if (frog1.intersect(trunk)) {
                                count2++;
                                if (count2 <= 1) {
                                    frog1.setLeft(frog1.getLeft() + trunk.getSpeed());
                                    frog1.setRight(frog1.getRight() + trunk.getSpeed());
                                }
                            }
                        }
                    }

                    if (frog1.getRange() > (separate + 1) && frog1.getRange() < ranges - 1 && count2 == 0) {
                        frog1.setTrunk_intersection(false);
                        player_collision = minim.loadFile(music_collision);
                        player_collision.setGain(-5);
                        player_collision.play();
                    }
                }

                if (game.getDiff() == "INFINITE") {  // 1 JOUEUR EN MODE INFINITY, ON AUGMENTE LA VITESSE DES VOITURES TOUTES LES 50 RANGEES
                    score_inf = frog1.getRange();

                    if (score_inf==count50){
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

                board.show_frog(im_frog, frog1);
                game.stateFrog(frog1,1);

            }

            if (game.getDiff() == "INFINITE"){
                board.create_text("Score : "+ score_inf, 20, grid/2, grid/2, 255, 255, 255);
                if (frog1.isGAMEOVER()) {
                    game.setGameState(true);
                    player_collision = minim.loadFile(music_collision);
                    player_collision.setGain(-5);
                    player_collision.play();
                    player_infinity.close();
                    player_defeat_infinity = minim.loadFile(music_defeat_infinity);
                    player_defeat_infinity.play();
                    remark = endless_treatment(record_infinity,score_inf);
                    board.create_case(game.getGame_width() / 2 -  6*grid, game.getGame_height() / 2 + 3*grid+10,game.getGame_width() / 2+7*grid,  game.getGame_height() / 2 -grid, 25, 25, 25);
                    board.create_text("Your score is " + score_inf, 32, game.getGame_width() / 2 - 3*grid, game.getGame_height() / 2, 0, 255, 0);
                    board.create_text(remark, 32, game.getGame_width() / 2 -  6*grid, game.getGame_height() / 2 + grid, 0, 255, 0);
                    board.create_text("Press the Space key to go back on Menu", 32, game.getGame_width() / 2 -  6*grid, game.getGame_height() / 2 + 3*grid, 255, 255, 0);
                    this.noLoop();
                }
            } else {
                board.create_text(t_i + "s", 20, grid / 2, grid / 2, 0, 0, 0);

                if (game.getGameState()) {

                    if (game.getDiff() == "EASY"){
                        player_easy.close();
                        player_victory_easy = minim.loadFile(music_victory_easy);
                        player_victory_easy.setGain(-2);
                        player_victory_easy.play();
                        remark = record_treatment(record, t_i);

                    } else if (game.getDiff() == "HARD"){
                        player_hard.close();
                        player_victory_hard = minim.loadFile(music_victory_hard);
                        player_victory_hard.setGain(-2);
                        player_victory_hard.play();
                        remark = record_treatment(record_hard, t_i);
                    }

                    board.create_case(game.getGame_width() / 2 -  6*grid, game.getGame_height() / 2 + 3*grid+10,game.getGame_width() / 2+8*grid,  game.getGame_height() / 2 -grid, 25, 25, 25);
                    board.create_text("Congratulations ! You beat Frogger in " + t_i + "s", 32, game.getGame_width() / 2 - 6 * grid, game.getGame_height() / 2, 0, 255, 0);
                    board.create_text(remark, 32, game.getGame_width() / 2 - 6 * grid, game.getGame_height() / 2 + grid, 0, 255, 0);
                    board.create_text("Press the Space key to go back on Menu", 32, game.getGame_width() / 2 -  6*grid, game.getGame_height() / 2 + 3*grid, 255, 255, 0);
                    this.noLoop();
                    if (buttonBack.click_event()){
                        game.setPlayerMode(null);
                        game.setDiff(null);
                    }

                }
            }
        }
    }


    /**
     * Associe les clicks sur le clavier a des actions particulieres
     */
    @Override
    public void keyPressed() {
        if (game.getGameState() && keyCode==Direction.SPACE){
            this.loop();
            game.reset_game();
        }
        if (game.getDiff()=="INFINITE"){
            if (keyCode == Direction.UP||keyCode == Direction.Z){
                player_jump = minim.loadFile(music_jump);
                player_jump.setGain(-2);
                player_jump.play();
                game.move_allCars(cars, grid);
                frog1.setRange(frog1.getRange()+1);
            }
            if (keyCode == Direction.RIGHT||keyCode == Direction.D){
                player_jump = minim.loadFile(music_jump);
                player_jump.setGain(-2);
                player_jump.play();
                frog1.move(grid, 0);
            }
            if (keyCode == Direction.LEFT||keyCode == Direction.Q){
                player_jump = minim.loadFile(music_jump);
                player_jump.setGain(-2);
                player_jump.play();
                frog1.move(-grid, 0);
            }
        }
        else {
            if (game.getPlayerMode()=="1 PLAYER"){
                if (keyCode == Direction.UP||keyCode == Direction.Z){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(0,- grid);

                }
                if (keyCode ==Direction.DOWN || keyCode == Direction.S){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(0, grid);

                }
                if (keyCode == Direction.RIGHT||keyCode == Direction.D){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(grid, 0);
                }
                if (keyCode == Direction.LEFT||keyCode == Direction.Q){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(-grid, 0);
                }
            }
            if (game.getPlayerMode()=="2 PLAYERS"){
                if (keyCode == Direction.UP){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog2.move(0,- grid);

                }
                if (keyCode ==Direction.DOWN){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog2.move(0, grid);

                }
                if (keyCode == Direction.RIGHT){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog2.move(grid,0);
                }
                if (keyCode == Direction.LEFT){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog2.move(-grid,0);
                }
                if (keyCode == Direction.Z){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(0,- grid);

                }
                if (keyCode ==Direction.Q){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(-grid,0);
                }
                if (keyCode == Direction.D){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(grid,0);
                }
                if (keyCode == Direction.S){
                    player_jump = minim.loadFile(music_jump);
                    player_jump.setGain(-2);
                    player_jump.play();
                    frog1.move(0,grid);

                }
            }
        }
    }
}
