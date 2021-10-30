package GameCommons;

import MovingElements.Frog;

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
     * @param num Le numéro de joueur associé à l'instance de Frog à réinitialiser
     */
    abstract void reset_Frog(Frog frog,  int num);
    /**
     * Réinitialise un Frog lorsqu'il meurt (contact avec voiture ou "tombée dans l'eau"). Change GameState en true en cas de victoire
     * @param frog L'instance de Frog à contrôler
     * @param num Le numéro de joueur associé à l'instance de Frog à contrôler
     */
    abstract void stateFrog(Frog frog,  int num);



}
