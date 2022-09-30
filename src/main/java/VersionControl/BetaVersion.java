package VersionControl;

import Strategies.*;

public class BetaVersion implements Version{
    @Override
    public Aging createAging() {
        return new BetaAging();
    }

    @Override
    public Winner createWinner() {
        return new BetaWinner();
    }

    @Override
    public UnitAction createUnitAction() {
        return null;
    }

    @Override
    public WorldMap createWorldMap() {
        return new AlphaWorldMap();
    }
}
