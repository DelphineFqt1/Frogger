package GameCommons;

import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import java.util.ArrayList;

public interface IEnvironment {

    /**
     * Créé la liste des voitures en rangée spécifiée
     * @param range
     *              La rangée commune des instances de Car de la liste
     * @return Une liste d'instances de Car rangée range, en nombre aléatoire entre 2 et 3, de même vitesse sélectionnée aléatoirement entre 4 crans et de taille aléatoire entre 2 crans
     */
    ArrayList<Car> car_range(int range);
    /**
     * créé la liste des troncs en rangée i
     *
     */
    ArrayList<Trunk> trunk_range(int range);
    /**
     * créé une liste de liste de voitures [[rangée 1], [rangée 2], ..., [range_end]]
     *
     */
    abstract ArrayList<ArrayList<Car>> allCars(int range_end);
    /**
     * créé une liste de liste de troncs [[rangée begin], [rangée 2], ..., [range_end]]
     *
     */
    abstract ArrayList<ArrayList<Trunk>> allTrunks(int range_begin, int range_end);
    /**
     * déplace chaque voiture d'une liste de liste de voitures selon les ordonnées
     *
     */
    abstract void move_allCars(ArrayList<ArrayList<Car>> allCars, int ydir);


}
