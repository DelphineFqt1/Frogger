package GameCommons;

import MovingElements.Frog;

/**
 * Regroupe les methodes dediees a la gestion des grenouilles, notamment leur initalisation et leur controle d'etat
 */
public interface IFrog {

/**
 * Initialise une instance de Frog en debut de partie
 * @param num
 *            Le numero du joueur, en pratique entre 1 et 2, n'a d'utilite qu'en PlayerMode == "2 PLAYERS"
 * @return Une instance de Frog, qui debute au milieu du bas de l'ecran en "1 PLAYER", ou alors respectivement a 1/4 et 3/4 du bas de l'ecran pour num ==1 et num==2
 */
    abstract Frog setFrog(int num);

    /**
     * Reinitialise une instance de Frog comme en debut de partie
     * @param frog L'instance de Frog a reinitialiser
     * @param num Le numero de joueur associe a l'instance de Frog a reinitialiser. Remarque : il est possible de changer de numero de joueur en cours de partie, mais cela est fortement deconseille
     */
    abstract void reset_Frog(Frog frog,  int num);

    /**
     * Reinitialise un Frog lorsqu'il meurt (contact avec une instance de Car ou detachement de toute instance de Trunk). Change GameState en true en cas de victoire
     * @param frog L'instance de Frog a controler
     * @param num Le numero de joueur associe a l'instance de Frog a controler. Remarque : il est possible de changer de numero de joueur en cours de partie, mais cela est fortement deconseille
     */
    abstract void stateFrog(Frog frog,  int num);



}
