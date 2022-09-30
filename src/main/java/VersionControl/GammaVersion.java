package VersionControl;

import Strategies.Aging;
import Strategies.UnitAction;
import Strategies.Winner;
import Strategies.WorldMap;

public class GammaVersion implements Version{
    @Override
    public Aging createAging() {
        return null;
    }

    @Override
    public Winner createWinner() {
        return null;
    }

    @Override
    public UnitAction createUnitAction() {
        return null;
    }

    @Override
    public WorldMap createWorldMap() {
        return null;
    }
}
