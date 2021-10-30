package GraphicalElements;

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




}

