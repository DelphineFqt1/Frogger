package GraphicalElements;

import GameCommons.Main;
import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


import java.util.ArrayList;

/**
 * La classe Element dispose de méthodes graphiques dont le support est une instance de PApplet, pris comme attribut
 */
public class Element implements IFroggerGraphics {

    PApplet P;

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
    public void create_text(String text, int size, int x, int y, int r, int g, int b) {
        P.textSize(size);
        P.fill(r, g, b);
        P.text(text, x, y);
    }

    @Override
    public void show_image(PImage img, float top, float bottom, float width, float height) {
        P.image(img, top, bottom, width, height);
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
        private String text;

        /**
         * Un objet Button spécifie un type de case particulier avec lequel on peut intéragir
         */
        public Button(PApplet P, int left, int top, int right, int bottom, String text) {
            super(P);
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.text = text;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public int getBottom() {
            return bottom;
        }

        public void setBottom(int bottom) {
            this.bottom = bottom;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public void create_case(float x, float y, float w, float h, float r, float g, float b) {
            super.create_case(x, y, w, h, r, g, b);
        }

        @Override
        public void create_text(String text, int size, int x, int y, int r, int g, int b) {
            super.create_text(text, size, x, y, r, g, b);
        }

/**
 * Affiche le bouton tout en le colorant lorsque la souris passe dessus
 */
        public void show(){
            this.create_case(left, top, right, bottom, 255,255,255);
            this.create_text(text, 25, left+(right-left)/3-10, top-(top-bottom)/4-5, 0,0,0);
            if (P.mouseX>left && P.mouseX<right &&P.mouseY<top &&P.mouseY>bottom){
                this.create_case(left, top, right, bottom, 200,0,0);
                this.create_text(text, 25, left+(right-left)/3-10, top-(top-bottom)/4-5, 0,0,0);
            }
        }
/**
 * Renvoit true lorsque l'on clique sur le bouton, et false dans le cas contraire
 */
        public boolean click_event(){
            return (P.mouseX>left && P.mouseX<right &&P.mouseY<top &&P.mouseY>bottom&& P.mousePressed);

        }

    }
}

