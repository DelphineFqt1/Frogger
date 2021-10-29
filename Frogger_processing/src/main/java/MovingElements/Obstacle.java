package MovingElements;

public abstract class Obstacle extends Moving{

    private final float abs_limit;
    private final float ord_lim;
    private float left;
    private float right;
    private float bottom;
    private float top;
    private float range;
    private float speed;
    private float width;

/**
 * Classe mère abstraite des obstacles du jeu (Car et Trunk). Un Obstacle prend en attribut des limites horizontales et verticales qu'il ne pourra pas dépasser
 */
    public Obstacle(float x, float y, float width, float height, float range, float speed, float abs_limit, float ord_lim) {
        super(x, y, width, height, range, speed);
        this.abs_limit = abs_limit;
        this.ord_lim = ord_lim;
    }

    public float getAbs_limit() {
        return abs_limit;
    }

    public float getOrd_lim() {
        return ord_lim;
    }

    /**
     *  Additionne respectivement xdir et ydir (pixels) aux coordonnées horizontales et verticales d'un Obstacle. Si l'obstacle déborde de la fenêtre, il est réinitialisé dans l'extrémité opposée de l'écran
     */
    @Override
    public void move(float xdir, float ydir) {
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
 * Classe représentant la voiture
 */
    public static class Car extends Obstacle{

        public Car(float x, float y, float width, float height, float range, float speed, float abs_limit, float ord_lim) {
            super(x, y, width, height, range, speed, abs_limit, ord_lim);
        }

        @Override
        public void move(float xdir, float ydir) {
            super.move(xdir, ydir);
        }
    }
    /**
     * Classe représentant le tronc
     */
    public static class Trunk extends Obstacle{
        public Trunk(float x, float y, float width, float height, float range, float speed, float abs_limit, float ord_lim) {
            super(x, y, width, height, range, speed, abs_limit, ord_lim);
        }

        @Override
        public void move(float xdir, float ydir) {
            super.move(xdir, ydir);
        }
    }
}
