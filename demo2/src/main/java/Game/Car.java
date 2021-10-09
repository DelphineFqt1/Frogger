package Game;

import processing.core.PApplet;

public class Car extends PApplet implements Action {

    float left;
    float right;
    float bottom;
    float top;
    float speed;
    int range;
    float width;

    public Car(float x, float y, float width, float height, int range, float spd){
        this.left = x;
        this.width = width;
        this.right = x+width;
        this.bottom = y;
        this.top = y+height;
        this.speed = spd;
        this.range = range;
    }

    void show(){
        Frogger_1.processing.fill(150,0,0);
        Frogger_1.processing.rect(left,top, right, bottom);
    }

//    void update(){
//        left += speed;
//        right += speed;
//        if (speed >0 && left> Frogger_1.largeur_fenetre){
//            left = -width-Frogger_1.grid;
//            right = left + width;
//        }
//        else if(speed <0 && right<0){
//            right = Frogger_1.largeur_fenetre + width;
//            left = right -width;
//        }
//    }


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
        range += add;
        return range;
    }

    @Override
    public float speed(float add) {
        speed += add;
        return speed;
    }

    @Override
    public boolean intersection(Action other) {
        return !((this.left(0)>=other.right(0)) ||
                (this.right(0) <= other.left(0))||
                (this.top(0)<= other.bottom(0))||
                (this.bottom(0)>= other.top(0)));
    }

   
    

    @Override
    public void move(float xdir, float ydir) {
        left += xdir;
        right += xdir;
        if (speed >0 && left> Frogger_1.largeur_fenetre){
            left = -(right - left)-Frogger_1.grid;
            right = left + width;
        }
        else if(speed <0 && right<0){
            right = Frogger_1.largeur_fenetre + (right-left);
            left = right -width;
        }

    }
}

