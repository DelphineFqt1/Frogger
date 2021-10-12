package Game;

import processing.core.PApplet;

public class Frog extends PApplet implements Action {

    float left;
    float right;
    float bottom;
    float top;
    int range;


    public Frog(float x, float y, float width){
        // ATTRIBUTS !
        this.left = x;
        this.right = x+width;
        this.bottom = y;
        this.top = y + width;
        this.range = 0;
    }

    void show(){
        Frogger_1.processing.fill(0,255,0,255);
        Frogger_1.processing.rectMode(CORNERS);
        Frogger_1.processing.rect(this.left, this.top, this.right, this.bottom);

    }



    // TRANSFORMATEURS !

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
        return 0;
    }

    @Override
    public boolean intersection(Action other) {
        return !(this.left(0)>= other.right(0) ||
                this.right(0) <= other.left(0) ||
                this.top(0)<=other.bottom(0)||
                this.bottom(0)>= other.top(0));
    }




    @Override
    public void move(float xdir, float ydir) {
//        left(xdir*50);
//        right(xdir*50);
//        top(ydir*50);
//        bottom(ydir*50);
//        if (left==0){
//            bottom(ydir*Frogger_1.grid);
//            top(ydir*Frogger_1.grid);
//        };
//        if (right==Frogger_1.largeur_fenetre-Frogger_1.largeur_fenetre%Frogger_1.grid){
//            bottom(ydir*Frogger_1.grid);
//            top(ydir*Frogger_1.grid);
//        }
//        if (bottom == Frogger_1.y_debut){
//            left(xdir*Frogger_1.grid);
//            right(xdir*Frogger_1.grid);
//        }
//        if (top == Frogger_1.hauteur_fenetre-Frogger_1.grid){
//            left(xdir*Frogger_1.grid);
//            right(xdir*Frogger_1.grid);
//        }

        left(xdir*Frogger_1.grid);
        right(xdir*Frogger_1.grid);
        bottom(ydir*Frogger_1.grid);
        top(ydir*Frogger_1.grid);
        if (ydir<0){
            range(1);
        }
        else if(ydir>0){
            range(-1);
        }



    }


}