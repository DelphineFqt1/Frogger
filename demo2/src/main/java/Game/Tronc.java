package Game;

import processing.core.PApplet;

public class Tronc extends PApplet implements Action{

    float left;
    float right;
    float bottom;
    float top;
    float speed;
    int range;
    float width;

    public Tronc(float x, float y, float width, float height, int range, float spd){
        this.left = x;
        this.right = x+width;
        this.bottom = y;
        this.width=width;
        this.top = y+height;
        this.speed = spd;
        this.range = range;
    }

    void show(){
        Frogger_1.processing.fill(88, 41, 0, 220);
        Frogger_1.processing.rect(left,top, right, bottom);
    }


    @Override
    public float left(float add) {
        this.left += add;
        return this.left;
    }

    @Override
    public float top(float add) {
        this.top += add;
        return this.top;
    }

    @Override
    public float right(float add) {
        this.right += add;
        return this.right;
    }

    @Override
    public float bottom(float add) {
        this.bottom += add;
        return this.bottom;
    }

    @Override
    public int range(int add) {
        this.range +=add;
        return this.range;
    }

    @Override
    public float speed(float add) {
        return 0;
    }

    @Override
    public boolean intersection(Action other) {
        return (this.range(0)==other.range(0)&&this.right(0)>other.right(0)&&this.left(0)<other.left(0));
    }


    @Override
    public void move(float xdir, float ydir) {
        left += speed;
        right += speed;
        if (speed >0 && left> Frogger_1.largeur_fenetre){
            left = - this.width;
            right = left + this.width;
        }
        else if(speed <0 && right<0){
            right = Frogger_1.largeur_fenetre + this.width;
            left = right -this.width;
        }

    }
}

