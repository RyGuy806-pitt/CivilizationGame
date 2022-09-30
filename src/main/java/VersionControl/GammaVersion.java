package VersionControl;

import Strategies.*;

public class GammaVersion implements Version{
    @Override
    public Aging createAging() {
        return new AlphaAging();
    }

    @Override
    public Winner createWinner() {
        return new AlphaWinner();
    }

    @Override
    public UnitAction createUnitAction() {
        return new GammaUnitAction();
    }

    @Override
    public WorldMap createWorldMap() {
        return new AlphaWorldMap();
    }
}
