package GraphicalElements;

import processing.core.PImage;

public interface IFroggerGraphics {
    /**
     *  fait apparaitre une case en ayant ses coordonnées, avec la couleur rgb spécifiée
     */
    abstract void create_case(float left, float top, float right, float bottom, float r, float g, float b);
    /**
     *  fait apparaitre un texte aux coordonnées et à la couleur rgb spécifiée
     */
    abstract void create_text(String text, int size, int x, int y, int r, int g, int b);
    /**
     *  affiche une arrière-plan en niveau de gris spécifié
     */
    abstract void background(int rgb);
    /**
     *  affiche une PImage en tant qu'arrière plan, avec la largeur (width) et hauteur (height) spécifiées
     */
    abstract void background_im(PImage img, int width, int height);
    /**
     *  initialise la largeur (width) et la hauteur (height) de la fenêtre de jeu
     */
    abstract void size(int width, int height);
    /**
     *  affiche une PImage aux coordonnées (left, bottom) et dimensions spécifiées (width et height)
     */
    abstract void show_image(PImage img, float left, float bottom, float width, float height);
}
