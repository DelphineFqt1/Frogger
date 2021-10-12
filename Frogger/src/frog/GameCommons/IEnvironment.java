package GameCommons;
import GraphicalElements.Element;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.util.ArrayList;

public interface IEnvironment {
//    abstract void reset_obstacle(Obstacle obstacle);
//    abstract void reset_trunk(Trunk trunk);
    abstract Car[] car_range(int range);
    abstract ArrayList<Trunk> trunk_range(int range);
    abstract int random_btw(int begin, int end);
    //abstract Car[] all_cars(int begin, int end);
    //abstract Trunk[] all_trunks(int begin, int end);
    //abstract void update_cars(ArrayList<Car> cars);
    //abstract void update_trunks(ArrayList<Trunk> trunks);
//    abstract void show_car(Car car);
//    abstract void show_trunk(Trunk trunk);
//    abstract ArrayList<Obstacle> defile_all(int begin_car, int end_car, int begin_trunk, int end_trunk);

}
