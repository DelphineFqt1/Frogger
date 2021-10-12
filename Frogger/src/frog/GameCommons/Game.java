package GameCommons;

import MovingElements.Obstacle;
import processing.core.PApplet;
import MovingElements.Frog;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.util.ArrayList;
import java.util.Random;


public class Game implements IFrog, IEnvironment{
    private int grid;
    private int ranges;
    private int columns;
    private int gameState;
    private int game_width;
    private int game_height;

    public Game(int grid, int ranges, int columns){
        this.grid = grid;
        this.ranges = ranges;
        this.columns = columns;
        this.game_width = columns*grid;
        this.game_height = ranges*grid;
        this.gameState = 0;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public int getRanges() {
        return ranges;
    }

    public void setRanges(int ranges) {
        this.ranges = ranges;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getGame_width() {
        return game_width;
    }

    public void setGame_width(int game_width) {
        this.game_width = game_width;
    }

    public int getGame_height() {
        return game_height;
    }

    public void setGame_height(int game_height) {
        this.game_height = game_height;
    }



//    @Override
//    public ArrayList<Car> defile_car_range(int y, int range) {
//
//        int number_cars = random_btw(2,4);
//        int x = random_btw(0, columns*grid);
//        int size = random_btw(2,4)*grid;
//        int speed = random_btw(-2,2);
//        ArrayList<Car> cars = new ArrayList<Car>();
//
//        for (int i=0; i<number_cars; i++){
//            Car car = new Car(x +i*(size + random_btw(10,50)), y , size, y+grid, range, speed, columns*grid);
//            cars.add(car);
//        }
//        return cars;
//    }
//
//    @Override
//    public ArrayList<Trunk> defile_trunk_range(int y, int range) {
//        int number_trunks = random_btw(2,4);
//        int x = random_btw(0, columns*grid);
//        int size = random_btw(3,4)*grid;
//        int speed = random_btw(-2,2);
//        ArrayList<Trunk> trunks = new ArrayList<Trunk>();
//
//        for (int i=0; i<number_trunks; i++){
//            Trunk trunk = new Trunk(x +i*(size + random_btw(10,50)), y , size, y+grid, range, speed,columns*grid);
//            trunks.add(trunk);
//        }
//        return trunks;
//    }
//
//    @Override
//    public ArrayList<Car> defile_cars(int begin, int end) {
//        ArrayList<Car> cars = new ArrayList<Car>();
//        for (int i = 0; i<=end-begin; i++){
//            ArrayList<Car> range_i = defile_car_range(begin + i*grid, i+begin);
//            cars.addAll(range_i);
//        }
//        return cars;
//    }
//
//    @Override
//    public ArrayList<Trunk> defile_trunks(int begin, int end) {
//        ArrayList<Trunk> trunks = new ArrayList<Trunk>();
//        for (int i = 0; i<=end-begin; i++){
//            ArrayList<Trunk> range_i = new ArrayList<Trunk>(); defile_trunk_range(begin + i*grid, i+begin);
//            trunks.addAll(range_i);
//        }
//        return trunks;
//    }
//
//    @Override
//    public void update_cars(ArrayList<Car> cars) {
//        for (int i=0; i<cars.size(); i++){
//            cars.get(i).move(cars.get(i).getSpeed()*grid, 0);
//        }
//    }
//
//    @Override
//    public void update_trunks(ArrayList<Trunk> trunks) {
//        for (int i=0; i<trunks.size(); i++){
//            trunks.get(i).move(trunks.get(i).getSpeed()*grid, 0);
//
//    }}



    @Override
    public Car[] car_range(int range) {
        int x = 0;
        int size = random_btw(2,4)*grid;
        int number = random_btw(2,4);
        int speed = random_btw(1,2);
        Car[] cars = new Car[number];
        for (int i=0; i<number; i ++){
            x = x +i*size + random_btw(10,50);
            cars[i]= new Car(x , game_height-(range+1)*grid,size, grid, range, speed, game_width);
        }
        return cars;
    }

    @Override
    public ArrayList<Trunk> trunk_range(int range) {
        int x = random_btw(0, game_width);
        int size = random_btw(3,4)*grid;
        int number = random_btw(2,4);
        int speed = random_btw(-2,2);
        ArrayList<Trunk> trunks = new ArrayList<Trunk>();
        for (int i=0; i<number; i ++){
            trunks.add(new Trunk(x +i*size + random_btw(10,50), game_height-(range+1)*grid,size, grid, range, speed, game_width));
        }
        return trunks;
    }

    @Override
    public int random_btw(int begin, int end) {
        Random rand = new Random();
        int nb_aleatoire = rand.nextInt(end-begin + 1)+begin;
        return nb_aleatoire;
    }

//    @Override
//    public ArrayList<Obstacle> defile_all(int begin_car, int end_car, int begin_trunk, int end_trunk) {
//        ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
//        ArrayList<Obstacle> cars = defile_cars(begin_car, end_car);
//        ArrayList<Obstacle> trunks = defile_trunks(begin_trunk, end_trunk);
//        obstacles.addAll(cars);
//        obstacles.addAll(trunks);
//        return obstacles;
//    }


    @Override
    public void deal_state_frog(Frog frog) {
        if ((frog.isCar_intersection()==frog.isTrunk_intersection()) || (frog.getLeft()<0 || frog.getRight()>this.game_width||frog.getBottom()<0||frog.getTop()>this.game_height)){
            setGameState(-1);
            System.out.println("GAME OVER");
            resetFrog(frog);
        }
        if (frog.getRange() == this.getRanges()-1){
            setGameState(1);
            System.out.println("GAME CLEARED");
        }

    }


    @Override
    public void resetFrog(Frog frog) {
        frog.setLeft(columns*grid/2-grid/2);
        frog.setRight(columns*grid/2-grid/2+grid);
        frog.setBottom(columns*grid-grid);
        frog.setTop(columns*grid );
        frog.setCar_intersection(false);
        frog.setTrunk_intersection(true);
        frog.setRange(0);

    }

    @Override
    public Frog set_Frog() {
        Frog frog = new Frog(this.game_width/2-grid/2,this.game_height-grid, grid) ;
        return frog;
    }

//    @Override
//    public void reset_obstacle(Obstacle o) {
//        if (o.getSpeed() >0 && o.getLeft()> o.getAbs_limit()){
//            o.setLeft(-o.getWidth());
//            o.setRight(o.getLeft() + o.getWidth());
//        }
//        else if(o.getSpeed() <0 && o.getRight()<0){
//            o.setRight(o.getAbs_limit()+ o.getWidth());
//            o.setLeft(o.getRight()-o.getWidth());
//        }
//    }
}
