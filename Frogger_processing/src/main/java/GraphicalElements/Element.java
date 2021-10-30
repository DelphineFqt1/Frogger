package GraphicalElements;

import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * La classe Element dispose de méthodes graphiques dont le support est une instance de PApplet, prise comme attribut
 */
public class Element implements IFroggerGraphics {

    PApplet P;

    /**
     * @param P Une instance de PApplet qui lance une IHM et qui sert de support à l'affichage des méthodes graphiques de la classe
     */
    public Element(PApplet P){
        this.P = P;
    }

    @Override
    public void create_case(float left, float top, float right, float bottom, float r, float g, float b) {
        P.fill(r,g,b);
        P.rectMode(P.CORNERS);
        P.rect(left, top, right, bottom);
    }

    @Override
    public void create_text(String text, int size, int x, int y, int r, int g, int b) {
        P.textSize(size);
        P.fill(r, g, b);
        P.text(text, x, y);
    }

    @Override
    public void show_image(PImage img, float left, float bottom, float width, float height) {
        try{
            P.image(img, left, bottom, width, height);
        }
        catch (NullPointerException e){
            create_case(left, bottom + height, left+width, bottom, 255,255,255);
            create_case(450, 80, 840, 0, 0,0,0);
            create_text("Warning : Running in Error mode\nPaths specified for images not found", 25, 450, 30, 255,0,0);
        }
    }

    @Override
    public void show_frog(PImage img, Frog frog) {
            show_image(img, frog.getLeft(), frog.getBottom(), frog.getWidth(), frog.getHeight());
    }

    @Override
    public void show_car(PImage img, Obstacle.Car car) {
            show_image(img, car.getLeft(), car.getBottom(), car.getWidth(), car.getHeight());
    }
    @Override
    public void show_trunk(PImage img, Obstacle.Trunk trunk) {
            show_image(img, trunk.getLeft(), trunk.getBottom(), trunk.getWidth(), trunk.getHeight());
    }

    @Override
    public void background(int rgb) {
        P.background(rgb);
    }

    @Override
    public void background_im(PImage image, int width, int height) {
        try{
        image.resize(width, height);
        P.background(image);
        }
        catch (NullPointerException e){
            background(0);
            create_case(350, 80, 840, 0, 0,0,0);
            create_text("Warning : Running in Error mode\nPath specified for background image not found", 25, 350, 30, 255,0,0);
        }
    }

    @Override
    public void size(int width, int height) {
        P.size(width, height);
    }




}

