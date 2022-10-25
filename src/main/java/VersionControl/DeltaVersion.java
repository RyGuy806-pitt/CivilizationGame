package VersionControl;

import Strategies.*;

public class DeltaVersion implements Version{
    @Override
    public Aging createAging() { return new AlphaAging(); }

    @Override
    public Winner createWinner() {
        return new AlphaWinner();
    }

    @Override
    public UnitAction createUnitAction() {
        return null;
    }

    @Override
    public WorldMap createWorldMap() {
        return new DeltaWorldMap();
    }

    @Override
    public Attack createAttack(){ return new AlphaAttack(); }
}
