package MovingElements;

public class Frog extends Moving{
    private int range;
    private boolean GAMEOVER;


    private boolean car_intersection;
    private boolean trunk_intersection;


    /**
     *  La grenouille est représentée par un carré, qui est en état de GAMEOVER lors d'un contact avec une voiture ou une sortie de tronc
     */
    public Frog(int x, int y, int width) {
        super(x, y, width, width, 0, 0);
        this.car_intersection = false; // signale si frog intersecte un car
        this.trunk_intersection = true; // signale si frog est sur un tronc ou pas
        this.GAMEOVER = false; // utile en mode infini où me GAMEOVER signifie la fin de la partie de jeu
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

    /**
     *  Additionne respectivement xdir et ydir (pixels) aux coordonnées horizontales et verticales d'un Frog
     */
    @Override
    public void move(int xdir, int ydir) {
        setLeft(getLeft() + xdir);
        setRight(getRight()+ xdir);
        setTop(getTop() + ydir);
        setBottom(getBottom() + ydir);
        if (ydir<0){
            this.range+=1;
        }
        else if(ydir>0){
            this.range-=1;
        }
    }

    /**
     *  Détermine si un Frog est en contact avec une voiture ou s'il est à l'intérieur d'un tronc
     */
    public boolean intersect(Obstacle obstacle){
        // Si l'obstacle est une voiture, on a besoin de savoir si frog touche la voiture ou non

        if (obstacle instanceof Obstacle.Car){
            return !((this.getLeft()>=obstacle.getRight()) ||
                    (this.getRight() <= obstacle.getLeft())||
                    (this.getTop()<= obstacle.getBottom())||
                    (this.getBottom()>= obstacle.getTop()));
        }
        // Si l'obstacle n'est pas une voiture (mais un tronc), on a besoin de savoir si frog est à l'intérieur d'un tronc ou non
        else {
            return (this.getRange() == obstacle.getRange() && (this.getRight() < obstacle.getRight() && this.getLeft() > obstacle.getLeft()));
        }
    }

}
