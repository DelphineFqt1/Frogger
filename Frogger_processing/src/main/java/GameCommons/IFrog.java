package GameCommons;

import MovingElements.Frog;

/**
 * Regroupe les méthodes dédiées à la gestion des grenouilles, notamment leur initalisation et leur contrôle d'état
 */
public interface IFrog {
/**
 * Initialise une instance de Frog en début de partie
 * @param num
 *            Le numéro du joueur, en pratique entre 1 et 2, n'a d'utilité qu'en PlayerMode == "2 PLAYERS"
 * @return Une instance de Frog, qui débute au milieu du bas de l'écran en "1 PLAYER", ou alors respectivement à 1/4 et 3/4 du bas de l'écran pour num ==1 et num==2
 */
    abstract Frog setFrog(int num);
    /**
     * Réinitialise une instance de Frog comme en début de partie
     * @param frog L'instance de Frog à réinitialiser
     * @param num Le numéro de joueur associé à l'instance de Frog à réinitialiser. Remarque : il est possible de changer de numéro de joueur en cours de partie, mais cela est fortement déconseillé
     */
    abstract void reset_Frog(Frog frog,  int num);
    /**
     * Réinitialise un Frog lorsqu'il meurt (contact avec une instance de Car ou détachement de toute instance de Trunk). Change GameState en true en cas de victoire
     * @param frog L'instance de Frog à contrôler
     * @param num Le numéro de joueur associé à l'instance de Frog à contrôler. Remarque : il est possible de changer de numéro de joueur en cours de partie, mais cela est fortement déconseillé
     */
    abstract void stateFrog(Frog frog,  int num);



}
