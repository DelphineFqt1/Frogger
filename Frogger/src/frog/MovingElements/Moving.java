package MovingElements;

// Classe abstraite qui décrit les éléments mouvants (et intéragissant entre eux) du jeu

public abstract class Moving {
    private float left;
    private float right;
    private float width;
    private float height;

    public void setLeft(float left) {
        this.left = left;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getLeft() {
        return left;
    }

    public float getRight() {
        return right;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getTop() {
        return top;
    }

    public float getBottom() {
        return bottom;
    }

    public float getRange() {
        return range;
    }

    public float getSpeed() {
        return speed;
    }

    private float top;
    private float bottom;
    private float range;
    private float speed;

    public Moving(float x, float y, float width, float height, float range, float speed){
        this.left = x;
        this.right = x+width;
        this.bottom = y;
        this.top = y + height;
        this. range = range;
        this.speed = speed;
    }

    public abstract void move(float xdir, float ydir);

}
