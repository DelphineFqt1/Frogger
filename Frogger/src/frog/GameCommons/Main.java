package GameCommons;

import MovingElements.Frog;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import processing.core.PApplet;
import GraphicalElements.Element;

import java.util.ArrayList;

public class Main extends PApplet {

    public static PApplet processing;

    Frog frog;
    ArrayList<ArrayList<Car>> cars;
    ArrayList<ArrayList<Trunk>> trunks;
    Element board;
    Game game;
    int ranges = 12;
    int columns = 12;
    int grid = 50;
    Car[] C1;
    Car[] C2;
    Car[] C3;
    Car[] C4;
    Car[] C5;
    Car[] C6;


    public static void main(String[] args) {
        PApplet.main(new String[]{Main.class.getName()});

    }

    @Override
    public void settings() {
        processing = this;
        board = new Element(processing);
        game = new Game(grid, ranges, columns);
        size(game.getGame_width(), game.getGame_height());
    }

    @Override
    public void setup() {
        frog = game.set_Frog();
        game.deal_state_frog(frog);

        C1 = game.car_range(1);
        C2 = game.car_range(2);
        for (int i=0; i<C1.length;i++){
            Car car = C1[i];
            car.move(car.getSpeed(),0);
            board.show_car(car);

            if (frog.intersect(car)){
                System.out.println("BIM");
                frog.setCar_intersection(true);
            }
        }


//        cars = new ArrayList<ArrayList<Car>>();
//        for (int i=1;i<7; i++ ){
//            cars.add(game.car_range(i));
//            }
//        trunks = new ArrayList<ArrayList<Trunk>>();
//        for (int i=8;i<11; i++ ){
//            trunks.add(game.trunk_range(i));
//        }

    }

    @Override
    public void draw() {
        background(0);
        for (int i=0; i<C1.length;i++){
            Car car = C1[i];
            car.move(car.getSpeed(),0);
            board.show_car(car);

            if (frog.intersect(car)){
                System.out.println("BIM");
                frog.setCar_intersection(true);
            }
            }
        for (int i=0; i<C2.length;i++){
            Car car = C2[i];
            car.move(car.getSpeed(),0);
            board.show_car(car);

            if (frog.intersect(car)){
                System.out.println("BIM");
                frog.setCar_intersection(true);
            }
        }

//        for (int i = 0; i<cars.size(); i++){
//            for (int j =0; j<cars.get(i).size();j++){
//                board.show_car(cars.get(i).get(j));
//                cars.get(i).get(j).move(cars.get(i).get(j).getSpeed()*grid,0);
//            }
//        }
//        for (int i = 0; i<trunks.size(); i++){
//            for (int j =0; j<trunks.get(i).size();j++){
//                board.show_trunk(trunks.get(i).get(j));
//                trunks.get(i).get(j).move(trunks.get(i).get(j).getSpeed()*grid,0);
//            }
//        }

        board.show_frog(frog);
        game.deal_state_frog(frog);
    }

    @Override
    public void keyPressed() {
        if (keyCode == UP){
            frog.move(0,-grid);
            frog.setRange(frog.getRange()+1);
            //System.out.println("bottom :" + frog.getBottom());

        }
        else if (keyCode == DOWN){
            frog.move(0,grid);
            frog.setRange(frog.getRange()-1);
            //System.out.println("bottom :" + frog.getBottom());

        }
        else if (keyCode == RIGHT){
            frog.move(grid, 0);
            //System.out.println("left :" + frog.getLeft());
        }
        else if (keyCode == LEFT){
            frog.move(-grid,0);
            //System.out.println("left :" + frog.getLeft());
        };
    }
}
