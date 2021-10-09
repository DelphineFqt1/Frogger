package Game;

public interface Action{
    public abstract float left(float add);
    public abstract float top(float add);
    public abstract float right(float add);
    public abstract float bottom(float add);
    public abstract int range(int add);
    public abstract float speed(float add);


    public abstract boolean intersection(Action other);
    public abstract void move(float xdir, float ydir);


}
