package GraphicalElements;

import GameCommons.Main;
import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


import java.util.ArrayList;

public class Element implements IFroggerGraphics {

    PApplet P;
    PImage im_frog;

    public Element(PApplet P){
        this.P = P;
    }

    @Override
    public void create_case(float x, float y, float w, float h, float r, float g, float b) {
        P.fill(r,g,b);
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
    public void show_image(PImage img, float a, float b, float c, float d) {

        P.image(img, a, b, c, d);
    }

    @Override
    public void background(int rgb) {
        P.background(rgb);
    }

    @Override
    public void background_im(PImage image, int width, int height) {
        image.resize(width, height);
        P.background(image);
    }

    @Override
    public void size(int width, int height) {
        P.size(width, height);
    }

    public static class Button extends Element{
        private int left;
        private int top;
        private int right;
        private int bottom;

        public Button(PApplet P, int left, int top, int right, int bottom) {
            super(P);
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        @Override
        public void create_case(float x, float y, float w, float h, float r, float g, float b) {
            super.create_case(x, y, w, h, r, g, b);
        }

        public void show_button(){
            this.create_case(left, top, right, bottom, 255,255,255);
            if (P.mouseX>left && P.mouseX<right &&P.mouseY<top &&P.mouseY>bottom){
                this.create_case(left, top, right, bottom, 200,0,0);
            }
        }

        public boolean click_event(){
            return (P.mouseX>left && P.mouseX<right &&P.mouseY<top &&P.mouseY>bottom&& P.mousePressed);

        }

    }
}

