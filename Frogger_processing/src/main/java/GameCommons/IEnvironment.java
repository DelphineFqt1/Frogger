package GameCommons;

import MovingElements.Obstacle.Car;
import MovingElements.Obstacle.Trunk;
import java.util.ArrayList;

/**
 * Regroupe les méthodes dédiées à la gestion de l'environnement, notamment l'initialisation des obstacles
 */
public interface IEnvironment {

    /**
     * Créé la liste des voitures en rangée spécifiée
     * @param range
     *              La rangée commune des instances de Car de la liste
     * @return Une liste d'instances de Car en rangée range, en nombre aléatoire entre 2 et 3, de même vitesse sélectionnée aléatoirement entre 4 crans et de taille (size) aléatoire sélectionnée entre 2 et 3
     */
    ArrayList<Car> car_range(int range);
    /**
     * Créé la liste des troncs en rangée spécifiée
     * @param range
     *              La rangée commune des instances de Trunk de la liste
     * @return Une liste d'instances de Trunk en rangée range, en nombre aléatoire entre 2 et 3, de même vitesse sélectionnée aléatoirement entre 4 crans et de taille (size) 3
     */
    ArrayList<Trunk> trunk_range(int range);
    /**
     * Créé une liste de rangées de voitures jusqu'à la rangée spécifiée
     * @param range_end La dernière rangée des instances de Car de la liste
     * @return une liste de listes (rangées) de Car, de la forme [[rangée 1], [rangée 2], ..., [range_end]]
     */
    abstract ArrayList<ArrayList<Car>> allCars(int range_end);
    /**
     * Créé une liste de rangées de troncs entre deux rangées spécifiées
     * @param range_begin La rangée de départ des instances de Trunk
     * @param range_end La rangée finale des instances de Trunk
     * @return une liste de listes (rangées) de Car, de la forme [[rangée begin], [rangée 2], ..., [range_end]]
     */
    abstract ArrayList<ArrayList<Trunk>> allTrunks(int range_begin, int range_end);
    /**
     * Déplace chaque voiture d'une liste de liste de voitures selon les ordonnées
     * @param allCars La liste des rangées de voitures qui sera déplacée
     * @param ydir Le pas de déplacement vers le bas des voitures, en nombre de pixels
     */
    abstract void move_allCars(ArrayList<ArrayList<Car>> allCars, int ydir);
    /**
     * Réinitialise les attributs du jeu PlayerMode, Diff et gameState
     */
    abstract void reset_game();


}
