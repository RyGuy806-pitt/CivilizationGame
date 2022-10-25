package VersionControl;

import Strategies.*;

public interface Version {
    Aging createAging();

    Winner createWinner();

    UnitAction createUnitAction();

    WorldMap createWorldMap();

    Attack createAttack();
}
