package VersionControl;

import Strategies.*;

public class ZetaVersion implements Version{
    @Override
    public Aging createAging() { return new AlphaAging(); }

    @Override
    public Winner createWinner() {
        return new ZetaWinner(new BetaWinner(), new EpsilonWinner()); }

    @Override
    public UnitAction createUnitAction() { return new AlphaUnitAction(); }

    @Override
    public WorldMap createWorldMap() { return new AlphaWorldMap(); }

    @Override
    public Attack createAttack(){ return new AlphaAttack(); }
}
