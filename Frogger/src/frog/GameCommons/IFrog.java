package GameCommons;

import MovingElements.Frog;

public interface IFrog {
    abstract void deal_state_frog(Frog frog);
    abstract void resetFrog(Frog frog);
    abstract Frog set_Frog();
}
