package GameCommons;

import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import java.util.ArrayList;

/**
 * Regroupe les methodes dediees a la gestion de l'environnement, notamment l'initialisation des obstacles
 */
public interface IEnvironment {

    /**
     * Cree la liste des voitures en rangee specifiee
     * @param range
     *              La rangee commune des instances de Car de la liste
     * @return Une liste d'instances de Car en rangee range, en nombre aleatoire entre 2 et 3, de meme vitesse selectionnee aleatoirement entre 4 crans et de taille (size) aleatoire selectionnee entre 2 et 3
     */
    ArrayList<Car> car_range(int range);

    /**
     * Cree la liste des troncs en rangee specifiee
     * @param range
     *              La rangee commune des instances de Trunk de la liste
     * @return Une liste d'instances de Trunk en rangee range, en nombre aleatoire entre 2 et 3, de meme vitesse selectionnee aleatoirement entre 4 crans et de taille (size) 3
     */
    ArrayList<Trunk> trunk_range(int range);

    /**
     * Cree une liste de rangees de voitures jusqu'a la rangee specifiee
     * @param range_end La derniere rangee des instances de Car de la liste
     * @return une liste de listes (rangees) de Car, de la forme [[rangee 1], [rangee 2], ..., [range_end]]
     */
    abstract ArrayList<ArrayList<Car>> allCars(int range_end);

    /**
     * Cree une liste de rangees de troncs entre deux rangees specifiees
     * @param range_begin La rangee de depart des instances de Trunk
     * @param range_end La rangee finale des instances de Trunk
     * @return une liste de listes (rangees) de Car, de la forme [[rangee begin], [rangee 2], ..., [range_end]]
     */
    abstract ArrayList<ArrayList<Trunk>> allTrunks(int range_begin, int range_end);

    /**
     * Deplace chaque voiture d'une liste de liste de voitures selon les ordonnees
     * @param allCars La liste des rangees de voitures qui sera deplacee
     * @param ydir Le pas de deplacement vers le bas des voitures, en nombre de pixels
     */
    abstract void move_allCars(ArrayList<ArrayList<Car>> allCars, int ydir);

    /**
     * Reinitialise les attributs du jeu PlayerMode, Diff et gameState
     */
    abstract void reset_game();
}
