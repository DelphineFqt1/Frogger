package GameCommons;

import MovingElements.Frog;

public interface IFrog {
/**
 * Initialise un Frog en fonction du PlayerMode et du numéro de joueur (num)
 */
    abstract Frog setFrog(int num);
    /**
     * Réinitialise un Frog comme en début de partie en fonction du PlayerMode et du numéro de joueur (num)
     */
    abstract void reset_Frog(Frog frog,  int num);
    /**
     * Réinitialise un Frog lorsqu'il meurt (contact avec voiture ou "tombée dans l'eau"). Change GameState en true en cas de victoire
     */
    abstract void stateFrog(Frog frog,  int num);



}
