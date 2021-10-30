package MovingElements;

/**
 * Classe mère abtraite de tous les objets mobiles du jeu (Frog, Car, Trunk)
 */
public abstract class Moving {
    private int left;
    private int right;
    private int width;
    private int height;
    private int top;
    private int bottom;
    private int range;
    private int speed;


    public Moving(int x, int y, int width, int height, int range, int speed){
        this.left = x;
        this.right = x+width;
        this.bottom = y;
        this.top = y + height;
        this. range = range;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public abstract void move(int xdir, int ydir);

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getRange() {
        return range;
    }

    public int getSpeed() {
        return speed;
    }

}
