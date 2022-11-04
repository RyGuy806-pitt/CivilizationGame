package Strategies;

import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public interface UnitAction {
    public void setUnitAction(GameImpl g, Position p, HashMap<Position, UnitImpl> UM, HashMap<Position, CityImpl> CM, HashMap<Position, TileImpl> TM);
}
