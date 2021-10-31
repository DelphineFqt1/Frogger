package GraphicalElements;

import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PImage;

/**
 * Regroupe toutes les méthodes graphiques mobilisées dans le jeu
 */
public interface IFroggerGraphics {
    /**
     *  Fait apparaitre une case en à partir de ses coordonnées et de la couleur rgb spécifiées
     * @param left L'abscisse du côté latéral gauche de la case
     * @param top L'ordonnée du côté supérieur de la case
     * @param right L'abscisse du côté latéral droit de la case
     * @param bottom L'ordonnée du côté inférieur de la case
     * @param r La valeur en nuance de rouge
     * @param g La valeur en nuance de vert
     * @param b La valeur en nuance de bleu
     */
    abstract void create_case(float left, float top, float right, float bottom, float r, float g, float b);
    /**
     *  Fait apparaitre un texte aux coordonnées (en pixels) et à la couleur rgb spécifiée
     * @param text Le texte à afficher
     * @param size La taille du texte
     * @param x L'abscisse du côté latéral gauche du "rectangle" de texte
     * @param y L'ordonnée du côté supérieur du "rectangle" de texte. Remarque : On prend en compte le fait que l'axe Oy est dirigé vers le bas
     * @param r La valeur en nuance de rouge
     * @param g La valeur en nuance de vert
     * @param b La valeur en nuance de bleu
     */
    abstract void create_text(String text, int size, int x, int y, int r, int g, int b);
    /**
     *  Affiche une arrière-plan en niveau de gris spécifié
     * @param rgb Le niveau de gris entre 0 et 255
     */
    abstract void background(int rgb);
    /**
     *  Affiche une PImage en tant qu'arrière plan, avec les dimensions spécifiées (en pixels)
     * @param img L'image à afficher en arrière plan
     * @param width La largeur de l'arrière-plan
     * @param height La hauteur de l'arrière-plan
     */
    abstract void background_im(PImage img, int width, int height);
    /**
     *  Initialise les dimensions de la fenêtre de jeu (en pixels)
     * @param width La largeur de la fenêtre de jeu
     * @param height La hauteur de la fenêtre de jeu
     */
    abstract void size(int width, int height);
    /**
     *  Affiche une PImage aux coordonnées et dimensions spécifiées (en pixels)
     * @param img L'image à afficher
     * @param left L'abscisse du côté latéral gauche de l'image
     * @param bottom L'ordonnée du côté inférieur de l'image
     * @param width La largeur de l'image
     * @param height La hauteur de l'image
     */
    abstract void show_image(PImage img, float left, float bottom, float width, float height);
    /**
     * Affiche une instance de Frog en tant que PImage
     * @param img L'image à afficher
     * @param frog L'instance de Frog à afficher
     */
    abstract void show_frog(PImage img, Frog frog);
    /**
     * Affiche une instance de Car en tant que PImage
     * @param img L'image à afficher
     * @param car L'instance de car à afficher
     */
    abstract void show_car(PImage img, Obstacle.Car car);
    /**
     * Affiche une instance de Trunk en tant que PImage
     * @param img L'image à afficher
     * @param trunk L'instance de trunk à afficher
     */
    abstract void show_trunk(PImage img, Obstacle.Trunk trunk);
}
