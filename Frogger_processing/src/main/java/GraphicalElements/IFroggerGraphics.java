package GraphicalElements;

import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public interface IFroggerGraphics {
    /**
     * / fait apparaitre une case en ayant ses coordonnées, avec la couleur rgb spécifiée
     */
    abstract void create_case(float left, float top, float right, float bottom, float r, float g, float b);
    /**
     * / Fait apparaitre la grenouille sous forme d'une PImage
     */
    abstract void show_frog(Frog frog, PImage icon);
    /**
     * / Fait apparaitre une voiture sous forme d'une PImage déclarée
     */
    abstract void show_car(Obstacle.Car car, PImage icon);
    /**
     * / Fait apparaitre unn tronc sous forme d'une PImage
     */
    abstract void show_trunk(Obstacle.Trunk trunk, PImage icon);
    /**
     * / fait apparaitre un texte aux coordonnées et à la couleur rgb spécifiée
     */
    abstract void create_text(String text, int size, int x, int y, int r, int g, int b);

    /**
     * / affiche une arrière-plan en niveau de gris spécifié
     */
    abstract void background(int rgb);
    /**
     * / affiche une PImage en tant qu'arrière plan, avec les dimensions spécifiées
     */
    abstract void background_im(PImage img, int width, int height);
    /**
     * / initialise la taille de la fenêtre de jeu
     */
    abstract void size(int width, int height);
    /**
     * / affiche une PImage aux coordonnées spécifiées
     */
    abstract void show_image(PImage img, float left, float top, float right, float bottom);
}
