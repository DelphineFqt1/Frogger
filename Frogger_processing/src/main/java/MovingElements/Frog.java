package MovingElements;

/**
 * Represente la grenouille, personnage controlable du jeu
 */
public class Frog extends Moving{
    private int range;
    private boolean GAMEOVER;


    private boolean car_intersection;
    private boolean trunk_intersection;


    /**
     * @param x L'abscisse du cote lateral gauche de la grenouille
     * @param y L'ordonnee du cote inferieur de la grenouille
     * @param width La largeur ainsi que la hauteur de la grenouille. Elle est donc modelisee par un carre
     */
    public Frog(int x, int y, int width) {
        super(x, y, width, width, 0, 0);
        this.car_intersection = false; // signale si frog intersecte un car
        this.trunk_intersection = true; // signale si frog est sur un tronc ou pas
        this.GAMEOVER = false; // utile en mode infini ou me GAMEOVER signifie la fin de la partie de jeu
    }



    /**
     *  Additionne respectivement parametres specifies (en pixels) aux coordonnees horizontales et verticales d'un Frog
     * @param xdir Le pas (en pixels) qui va s'ajouter aux coordonnees horizontales (axe Ox) de la grenouille
     * @param ydir Le pas (en pixels) qui va s'ajouter aux coordonnees verticales (axe Oy) de la grenouille
     */
    @Override
    public void move(int xdir, int ydir) {
        setLeft(getLeft() + xdir);
        setRight(getRight()+ xdir);
        setTop(getTop() + ydir);
        setBottom(getBottom() + ydir);
        if (ydir<0){
            setRange(getRange()+1);
        }
        else if(ydir>0){
            setRange(getRange()-1);
        }
    }

    /**
     *  Determine si la grenouille est en intersection avec une instance d'Obstacle (Car ou Trunk)
     * @param obstacle L'obstacle avec lequel compare la position relative de la grenouille
     * @return Un booleen qui indique si oui ou non la grenouille intersecte l'obstacle.
     * Remarque : l'intersection de la grenouille avec une voiture se resume a un simple contact avec, tandis que pour un tronc, il faut qu'elle y soit completement a l'interieur
     */
    public boolean intersect(Obstacle obstacle){
        // Si l'obstacle est une voiture, on a besoin de savoir si frog touche la voiture ou non

        if (obstacle instanceof Obstacle.Car){
            return !((this.getLeft()>=obstacle.getRight()) ||
                    (this.getRight() <= obstacle.getLeft())||
                    (this.getTop()<= obstacle.getBottom())||
                    (this.getBottom()>= obstacle.getTop()));
        }
        // Si l'obstacle n'est pas une voiture (mais un tronc), on a besoin de savoir si frog est a l'interieur d'un tronc ou non
        else {
            return (this.getRange() == obstacle.getRange() && (this.getRight() < obstacle.getRight() && this.getLeft() > obstacle.getLeft()));
        }
    }

    public boolean isGAMEOVER() {
        return GAMEOVER;
    }

    public void setGAMEOVER(boolean GAMEOVER) {
        this.GAMEOVER = GAMEOVER;
    }

    public boolean isCar_intersection() {
        return car_intersection;
    }

    public void setCar_intersection(boolean car_intersection) {
        this.car_intersection = car_intersection;
    }

    public boolean isTrunk_intersection() {
        return trunk_intersection;
    }

    public void setTrunk_intersection(boolean trunk_intersection) {
        this.trunk_intersection = trunk_intersection;
    }

}
