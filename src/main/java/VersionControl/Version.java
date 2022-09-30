package VersionControl;

import Strategies.Aging;
import Strategies.UnitAction;
import Strategies.Winner;
import Strategies.WorldMap;

public interface Version {
    Aging createAging();

    Winner createWinner();

    UnitAction createUnitAction();

    WorldMap createWorldMap();
}
