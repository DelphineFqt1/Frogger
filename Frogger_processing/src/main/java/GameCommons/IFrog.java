package GameCommons;

import MovingElements.Frog;

public interface IFrog {
    abstract void deal_state_frog(Frog frog); //En 1P, détermine l'état de mort ou de victoire de la grenouille
    // tout en agissant sur elle (reset en cas d'intersection de voitures par exemple)
    abstract void resetFrog(Frog frog); //Reconfigure la grenouille comme à l'état initial en 1 PLAYER
    abstract Frog set_Frog(); //Initialisation de la grenouille en début de jeu en 1 PLAYER
    abstract Frog set_Frog2P1(); //Initialisation de la grenouille gauche en début de jeu en 2 PLAYERS
    abstract Frog set_Frog2P2(); //Initialisation de la grenouille droite en début de jeu en 2 PLAYERS
    abstract void resetFrog2P1(Frog frog); //Reconfigure la grenouille gauche comme à l'état initial en 2 PLAYERS
    abstract void resetFrog2P2(Frog frog); //Reconfigure la grenouille droite comme à l'état initial en 2 PLAYERS
    abstract void deal_state_frog2P1(Frog frog); //En 2P, détermine l'état de mort ou de victoire de la grenouille gauche
    // tout en agissant sur elle
    abstract void deal_state_frog2P2(Frog frog); //En 2P, détermine l'état de mort ou de victoire de la grenouille droite
    // tout en agissant sur elle


}
