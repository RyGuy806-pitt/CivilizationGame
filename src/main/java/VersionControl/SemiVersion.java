package VersionControl;

import Strategies.*;

public class SemiVersion implements Version{
    @Override
    public Aging createAging() {
        return new BetaAging();
    }

    @Override
    public Winner createWinner() {
        return new EpsilonWinner();
    }

    @Override
    public UnitAction createUnitAction() {
        return new GammaUnitAction();
    }

    @Override
    public WorldMap createWorldMap() {
        return new DeltaWorldMap();
    }

    @Override
    public Attack createAttack(){ return new EpsilonAttack(); }
}
