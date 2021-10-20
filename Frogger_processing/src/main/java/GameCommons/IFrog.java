package GameCommons;

import MovingElements.Frog;

public interface IFrog {
    abstract void deal_state_frog(Frog frog);
    abstract void resetFrog(Frog frog);
    abstract Frog set_Frog();
    abstract Frog set_Frog2P1();
    abstract Frog set_Frog2P2();
    abstract void resetFrog2P1(Frog frog);
    abstract void resetFrog2P2(Frog frog);
    abstract void deal_state_frog2P1(Frog frog);
    abstract void deal_state_frog2P2(Frog frog);


}
