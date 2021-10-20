package GameCommons;
import GraphicalElements.Element;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public interface IEnvironment {
    abstract ArrayList<Car> car_range(int range);
    abstract ArrayList<Trunk> trunk_range(int range);
    abstract String record_treatment(Path f, float t);
    abstract int random_btw(int begin, int end);
}
