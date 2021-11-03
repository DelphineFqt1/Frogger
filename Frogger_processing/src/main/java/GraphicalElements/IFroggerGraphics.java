package GraphicalElements;

import MovingElements.Frog;
import MovingElements.Obstacle;
import processing.core.PImage;

/**
 * Regroupe toutes les methodes graphiques mobilisees dans le jeu
 */
public interface IFroggerGraphics {

    /**
     *  Fait apparaitre une case en a partir de ses coordonnees et de la couleur rgb specifiees
     * @param left L'abscisse du cote lateral gauche de la case
     * @param top L'ordonnee du cote superieur de la case
     * @param right L'abscisse du cote lateral droit de la case
     * @param bottom L'ordonnee du cote inferieur de la case
     * @param r La valeur en nuance de rouge
     * @param g La valeur en nuance de vert
     * @param b La valeur en nuance de bleu
     */
    abstract void create_case(float left, float top, float right, float bottom, float r, float g, float b);

    /**
     *  Fait apparaitre un texte aux coordonnees (en pixels) et a la couleur rgb specifiee
     * @param text Le texte a afficher
     * @param size La taille du texte
     * @param x L'abscisse du cote lateral gauche du "rectangle" de texte
     * @param y L'ordonnee du cote superieur du "rectangle" de texte. Remarque : On prend en compte le fait que l'axe Oy est dirige vers le bas
     * @param r La valeur en nuance de rouge
     * @param g La valeur en nuance de vert
     * @param b La valeur en nuance de bleu
     * @throws NullPointerException si le texte a afficher est null. Peut survenir lorsque le texte a affiher provient d'un fichier illisible ou inexistant
     */
    abstract void create_text(String text, int size, int x, int y, int r, int g, int b);

    /**
     *  Affiche une arriere-plan en niveau de gris specifie
     * @param rgb Le niveau de gris entre 0 et 255
     */
    abstract void background(int rgb);

    /**
     *  Affiche une PImage en tant qu'arriere plan, avec les dimensions specifiees (en pixels)
     * @param img L'image a afficher en arriere plan
     * @param width La largeur de l'arriere-plan
     * @param height La hauteur de l'arriere-plan
     * @throws NullPointerException si l'image est null. Peut survenir lorsque l'image est chargee a partir d'un chemin qui ne pointe pas vers pas une image, ou mal declare
     */
    abstract void background_im(PImage img, int width, int height);

    /**
     *  Initialise les dimensions de la fenetre de jeu (en pixels)
     * @param width La largeur de la fenetre de jeu
     * @param height La hauteur de la fenetre de jeu
     */
    abstract void size(int width, int height);

    /**
     *  Affiche une PImage aux coordonnees et dimensions specifiees (en pixels)
     * @param img L'image a afficher
     * @param left L'abscisse du cote lateral gauche de l'image
     * @param bottom L'ordonnee du cote inferieur de l'image
     * @param width La largeur de l'image
     * @param height La hauteur de l'image
     */
    abstract void show_image(PImage img, float left, float bottom, float width, float height);

    /**
     * Affiche une instance de Frog en tant que PImage
     * @param img L'image a afficher
     * @param frog L'instance de Frog a afficher
     * @throws NullPointerException si l'image est null. Peut survenir lorsque l'image est chargee a partir d'un chemin qui ne pointe pas vers pas une image, ou mal declare
     */
    abstract void show_frog(PImage img, Frog frog);

    /**
     * Affiche une instance de Car en tant que PImage
     * @param img L'image a afficher
     * @param car L'instance de car a afficher
     */
    abstract void show_car(PImage img, Obstacle.Car car);

    /**
     * Affiche une instance de Trunk en tant que PImage
     * @param img L'image a afficher
     * @param trunk L'instance de trunk a afficher
     */
    abstract void show_trunk(PImage img, Obstacle.Trunk trunk);
}
