package GraphicalElements;

import GameCommons.Main;
import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


import java.util.ArrayList;

public class Element extends PApplet implements IFroggerGraphics {

    PApplet P;
    PImage im_frog;



    public Element(PApplet P){
        this.P = P;
    }

    @Override
    public void create_case(float x, float y, float w, float h, float r, float g, float b) {
        P.fill(r,g,b);
        //P.noStroke();
        P.rectMode(P.CORNERS);
        P.rect(x, y, w, h);
    }


    @Override
    public void show_frog(Frog frog, PImage icon) {
//        im_frog = P.loadImage("C:\\Users\\alexa\\IdeaProjects\\FroggerV2\\src\\main\\java\\Images\\frog2.png");
        try {
            this.P.image(icon, frog.getLeft(), frog.getBottom(), frog.getWidth(), frog.getHeight());
        }
        catch (NullPointerException e) {
            System.out.println("L'adresse spécifée pour l'image de la grenouille n'est pas bonne");
        }
    }

    @Override
    public void show_car(Obstacle.Car car, PImage icon) {

        this.P.image(icon, car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight());

    }
    @Override
    public void show_trunk(Obstacle.Trunk trunk, PImage icon) {

        this.P.image(icon, trunk.getLeft(), trunk.getBottom(), trunk.getWidth(), trunk.getHeight());
    }

    @Override
    public void create_text(String text, int size, int x, int y, int r, int g, int b) {
        P.textSize(size);
        P.fill(r, g, b);
        P.text(text, x, y);
    }

    @Override
    public void image(PImage img, float a, float b, float c, float d) {
        P.image(img, a, b, c, d);
    }

}

