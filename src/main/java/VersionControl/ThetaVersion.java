package VersionControl;

import Strategies.*;

public class ThetaVersion implements Version{
    @Override
    public Aging createAging() { return new AlphaAging(); }

    @Override
    public Winner createWinner() { return new AlphaWinner(); }

    @Override
    public UnitAction createUnitAction() { return new ThetaUnitAction(); }

    @Override
    public WorldMap createWorldMap() { return new AlphaWorldMap(); }

    @Override
    public Attack createAttack(){ return new AlphaAttack(); }
}
