package GraphicalElements;

import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public interface IFroggerGraphics {
    abstract void create_case(float left, float top, float right, float bottom, float r, float g, float b);
    // fait apparaitre une case en ayant ses coordonnées, avec la couleur rgb spécifiée
    abstract void show_frog(Frog frog, PImage icon); //Fait apparaitre la grenouille sous forme d'une image déclarée
    abstract void show_car(Obstacle.Car car, PImage icon); //Fait apparaitre une voiture sous forme d'une image déclarée
    abstract void show_trunk(Obstacle.Trunk trunk, PImage icon); //Fait apparaitre unn tronc sous forme d'une image déclarée
    abstract void create_text(String text, int size, int x, int y, int r, int g, int b);
    // fait apparaitre un texte en ayant ses coordonnées, avec la couleur rgb spécifiée
}
