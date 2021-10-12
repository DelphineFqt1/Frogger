package MovingElements;

public class Obstacle extends Moving{

    private final float abs_limit;
    private float left;
    private float right;
    private float bottom;
    private float top;
    private float range;
    private float speed;
    private float width;


    public Obstacle(float x, float y, float width, float height, float range, float speed, float abs_limit) {
        super(x, y, width, height, range, speed);
        this.abs_limit = abs_limit;
    }

    public float getAbs_limit() {
        return abs_limit;
    }


    @Override
    public void move(float xdir, float ydir) {
        setLeft(getLeft()+ xdir);
        setRight(getRight()+ xdir);
        if (getSpeed() >0 && getLeft()> getAbs_limit()){
            setLeft(-getWidth());
            setRight(getLeft() + getWidth());
        }
        else if(getSpeed() <0 && getRight()<0){
            setRight(getAbs_limit()+ getWidth());
            setLeft(getRight()-getWidth());

    }
    }

    public static class Car extends Obstacle{

        public Car(float x, float y, float width, float height, float range, float speed, float abs_limit) {
            super(x, y, width, height, range, speed, abs_limit);
        }

        @Override
        public void move(float xdir, float ydir) {
            super.move(xdir, ydir);
        }
    }

    public static class Trunk extends Obstacle{
        public Trunk(float x, float y, float width, float height, float range, float speed, float abs_limit) {
            super(x, y, width, height, range, speed, abs_limit);
        }

        @Override
        public void move(float xdir, float ydir) {
            super.move(xdir, ydir);
        }
    }
}
