package VersionControl;

import Strategies.*;

public class AlphaVersion implements Version{
    @Override
    public Aging createAging() {
        return new AlphaAging();
    }

    @Override
    public Winner createWinner() { return new AlphaWinner(); }

    @Override
    public UnitAction createUnitAction() {  return null; }

    @Override
    public WorldMap createWorldMap() { return new AlphaWorldMap(); }
}
