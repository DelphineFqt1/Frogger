package GameCommons;
import GraphicalElements.Element;
import MovingElements.Obstacle;
import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public interface IEnvironment {

    /**
     * créé la liste des voitures en rangée i
     * /
     */
    ArrayList<Car> car_range(int range);
    /**
     * créé la liste des troncs en rangée i
     * /
     */
    ArrayList<Trunk> trunk_range(int range);
    /**
     * créé une liste de liste de voitures [[rangée 1], [rangée 2], ..., [range_end]]
     * /
     */
    abstract ArrayList<ArrayList<Car>> allCars(int range_end);
    /**
     * créé une liste de liste de troncs [[rangée begin], [rangée 2], ..., [range_end]]
     * /
     */
    abstract ArrayList<ArrayList<Trunk>> allTrunks(int range_begin, int range_end);
    /**
     * déplace chaque voiture d'une liste de liste de voitures selon les ordonnées
     * /
     */
    abstract void move_allCars(ArrayList<ArrayList<Car>> allCars, float ydir);


}
