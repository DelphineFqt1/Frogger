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


    public static class Car extends Obstacle{

        public Car(float x, float y, float width, float height, float range, float speed, float abs_limit, float ord_lim) {
            super(x, y, width, height, range, speed, abs_limit, ord_lim);
        }

        @Override
        public void move(float xdir, float ydir) {
            super.move(xdir, ydir);
        }
    }

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
