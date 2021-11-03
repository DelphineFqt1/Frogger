package MovingElements;

/**
 * Classe mere abstraite des obstacles du jeu (Car et Trunk). Un Obstacle prend en attribut des limites horizontales et verticales qu'il ne pourra pas depasser
 */
public abstract class Obstacle extends Moving{

    private final int abs_limit;
    private final int ord_lim;
    private int left;
    private int right;
    private int bottom;
    private int top;
    private int range;
    private int speed;
    private int width;


    public Obstacle(int x, int y, int width, int height, int range, int speed, int abs_limit, int ord_lim) {
        super(x, y, width, height, range, speed);
        this.abs_limit = abs_limit;
        this.ord_lim = ord_lim;
    }

    public int getAbs_limit() {
        return abs_limit;
    }

    public int getOrd_lim() {
        return ord_lim;
    }

    /**
     *  Additionne respectivement les parametres specifies (en pixels) aux coordonnees d'un Obstacle. Si l'obstacle deborde de la fenetre, il est reinitialise a l'extremite opposee de l'ecran
     * @param xdir Le pas (en pixels) qui va s'ajouter aux coordonnees horizontales (axe Ox) de l'obstacle
     * @param ydir Le pas (en pixels) qui va s'ajouter aux coordonnees verticales (axe Oy) de l'obstacle
     */
    @Override
    public void move(int xdir, int ydir) {
        setLeft(getLeft()+ xdir);
        setRight(getRight()+ xdir);
        setTop(getTop() + ydir);
        setBottom(getBottom()+ydir);
        if (getSpeed() >0 && getLeft()>= getAbs_limit()){
            setLeft(-getWidth());
            setRight(0);
        }
        else if(getSpeed() <0 && getRight()<=0) {
            setLeft(getAbs_limit());
            setRight(getAbs_limit() + getWidth());
        }
        if (getBottom() >= getOrd_lim() ){
            setBottom(0);
            setTop(getHeight());
        }
        }

/**
 * Classe representant la voiture
 */
    public static class Car extends Obstacle{

        public Car(int x, int y, int width, int height, int range, int speed, int abs_limit, int ord_lim) {
            super(x, y, width, height, range, speed, abs_limit, ord_lim);
        }

        @Override
        public void move(int xdir, int ydir) {
            super.move(xdir, ydir);
        }
    }

    /**
     * Classe representant le tronc
     */
    public static class Trunk extends Obstacle{
        public Trunk(int x, int y, int width, int height, int range, int speed, int abs_limit, int ord_lim) {
            super(x, y, width, height, range, speed, abs_limit, ord_lim);
        }

        @Override
        public void move(int xdir, int ydir) {
            super.move(xdir, ydir);
        }
    }
}
