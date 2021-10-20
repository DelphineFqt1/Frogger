package MovingElements;

public class Frog extends Moving{
    private float left;
    private float right;
    private float bottom;
    private float top;
    private float range;



    private float width;
    private boolean car_intersection;
    private boolean trunk_intersection;


    // La grenouille est un carré
    public Frog(float x, float y, float width) {
        super(x, y, width, width, 0, 0);
        this.car_intersection = false;
        this.trunk_intersection = true;
    }

    public boolean isCar_intersection() {
        return car_intersection;
    }

    public void setCar_intersection(boolean car_intersection) {
        this.car_intersection = car_intersection;
    }

    public boolean isTrunk_intersection() {
        return trunk_intersection;
    }

    public void setTrunk_intersection(boolean trunk_intersection) {
        this.trunk_intersection = trunk_intersection;
    }

    // mouvement horizontal et vertical selon xdir et ydir (pixels)
    @Override
    public void move(float xdir, float ydir) {
        setLeft(getLeft() + xdir);
        setRight(getRight()+ xdir);
        setTop(getTop() + ydir);
        setBottom(getBottom() + ydir);
        if (ydir<0){
            this.range+=1;
        }
        else if(ydir>0){
            this.range-=1;
        }
    }

    public boolean intersect(Obstacle obstacle){
        // Si l'obstacle est une voiture, on a besoin de savoir si frog touche la voiture ou non

        if (obstacle instanceof Obstacle.Car){
            return !((this.getLeft()>=obstacle.getRight()) ||
                    (this.getRight() <= obstacle.getLeft())||
                    (this.getTop()<= obstacle.getBottom())||
                    (this.getBottom()>= obstacle.getTop()));
        }
        // Si l'obstacle n'est pas une voiture (mais un tronc), on a besoin de savoir si frog est à l'intérieur d'un tronc ou non
        else {
            return (this.getRange() == obstacle.getRange() && (this.getRight() < obstacle.getRight() && this.getLeft() > obstacle.getLeft()));
        }
    }

//    public void car_intersection_modifier(Obstacle.Car car){
//        this.setCar_intersection(this.intersect(car));
//    }
//    public void trunk_intersection_modifier(Obstacle.Trunk trunk){
//        this.setTrunk_intersection(this.intersect(trunk));
//
//    }
}
