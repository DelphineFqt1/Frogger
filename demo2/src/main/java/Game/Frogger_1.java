// Fortement inspiré des vidéos youtube de The Coding Train

package Game;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.event.KeyEvent;

import java.nio.file.Paths;





public class Frogger_1 extends PApplet {

    public static PApplet processing;

    Frog frog;
    Car[] cars;
    Tronc[] trunks;
    static float grid = 40;
    static int largeur_fenetre = 900;
    static int hauteur_fenetre = 700;
    static float y_debut = hauteur_fenetre-2*grid+10;
    PImage bg;


    public static void main(String[] args) {
        PApplet.main(new String[]{Frogger_1.class.getName()});

    }

    public void resetGame(){
        frog = new Frog(largeur_fenetre/2-grid/2, hauteur_fenetre-2*grid+10, grid);
    }
    @Override
    public void settings() {
        size(largeur_fenetre, hauteur_fenetre);
        processing = this;

    }

    @Override
    public void setup() {
        bg=loadImage("C:\\Users\\alexa\\IdeaProjects\\demo2\\src\\main\\java\\Images\\background.png", "png");
        bg.resize(largeur_fenetre, hauteur_fenetre);
        cars = new Car[19];
        //frog = new Frog(275, 550, grid);

        trunks = new Tronc[12];
        resetGame();

        int index = 0;

        float decalage=2;

        //Rangée 1
        for ( int i = 0; i<3; i ++){
            float x = i*300 ;
            cars[index] = new Car(x, y_debut-grid, grid*2, grid ,1, 1);
            index ++;
        }

        // Rangée 2
        for (int i=0; i<3; i++){
            float x = i*350;
            cars[index] = new Car(x+30, y_debut-2*grid,grid*3, grid,2, 2);
            index ++;
        }

        //Rangée 3
        for (int i=0; i<2; i++){
            float x = largeur_fenetre + i*200;
            cars[index] = new Car(x, y_debut-3*grid,grid*3/2, grid,3, -1);
            index ++;
        }

        // Rangée 4
        for (int i =0; i<3;i++){
            float x = i*300;
            cars[index]= new Car(x+40, y_debut-4*grid, grid*2, grid, 4, -1);
            index ++;
        }
        // Rangée 5
        for (int i =0; i<3;i++){
            float x = i*400;
            cars[index]= new Car(x+40, y_debut-5*grid, grid*3, grid, 5, 1);
            index ++;
        }
        // Rangée 6
        for (int i =0; i<3;i++){
            float x = i*400;
            cars[index]= new Car(x+40, y_debut-6*grid, grid*2, grid, 6, -1);
            index ++;
        }
        // Rangée 7
        for (int i =0; i<2;i++){
            float x = i*200;
            cars[index]= new Car(x+40, y_debut-7*grid+5, grid*3, grid-5, 7, 2);
            index ++;
        }

        // Rangée 9

        int i_trunk = 0;
        for (int i=0; i<2; i++){
            float x = i*300;
            trunks[i_trunk]= new Tronc(x,y_debut-9*grid, grid*4, grid, 9, 1);
            i_trunk++;
        }
        //Rangée 10
        for (int i=0; i<2; i++){
            float x = i*300;
            trunks[i_trunk]= new Tronc(largeur_fenetre-x, y_debut-10*grid, grid*4, grid, 10, -1);
            i_trunk++;
        }

        //Rangée 11
        for (int i=0; i<2; i++){
            float x = i*300;
            trunks[i_trunk]= new Tronc(x+20, y_debut-11*grid, grid*5, grid, 11, 2);
            i_trunk++;
        }

        //Rangée 12
        for (int i=0; i<2; i++){
            float x = i*400;
            trunks[i_trunk]= new Tronc(largeur_fenetre-x, y_debut-12*grid, grid*4, grid, 12, -1);
            i_trunk++;
        }
        //Rangée 13
        for (int i=0; i<2; i++){
            float x = i*300;
            trunks[i_trunk]= new Tronc(largeur_fenetre-x, y_debut-13*grid, grid*4, grid, 13, 2);
            i_trunk++;
        }
        //Rangée 14
        for (int i=0; i<2; i++){
            float x = i*400;
            trunks[i_trunk]= new Tronc(largeur_fenetre-x, y_debut-14*grid, grid*5, grid, 14, -2);
            i_trunk++;
        }
    }

    @Override
    public void draw() {
        background(bg);
        //tint(255, 100);



        for (Car car : cars){

            car.move(car.speed, 0);
            car.show();
            if (frog.intersection(car)){

                resetGame();
                System.out.println("GAME OVER ");
            }
            }
        int count = 0;
        for (Tronc tronc : trunks){
            tronc.move(tronc.speed, 0);
            tronc.show();

            if (tronc.intersection(frog)){
                frog.left += tronc.speed;
                frog.right+= tronc.speed;
                count++;
            }

        }
        frog.show();
        if (frog.range>=9 &&frog.range<15 && count==0){
            resetGame();
            System.out.println("GAME OVER ");
        }
        if (frog.range >= 15){
            System.out.println("GAME CLEARED");
        }
        }


    @Override
    public void keyPressed() {
            if (keyCode == UP){
                frog.move(0,-1);

            }
            else if (keyCode == DOWN){
                frog.move(0,1);

            }
            else if (keyCode == RIGHT){
                frog.move(1, 0);
            }
            else if (keyCode == LEFT){
                frog.move(-1,0);
            };
        }


}

