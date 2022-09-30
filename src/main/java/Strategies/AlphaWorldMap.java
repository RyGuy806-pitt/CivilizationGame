package Strategies;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

import static hotciv.framework.GameConstants.ARCHER;
import static hotciv.framework.GameConstants.LEGION;
import static hotciv.framework.GameConstants.SETTLER;
import static hotciv.framework.GameConstants.OCEANS;
import static hotciv.framework.GameConstants.MOUNTAINS;
import static hotciv.framework.GameConstants.HILLS;

public class AlphaWorldMap implements WorldMap{
    Position oceanPos = new Position(1,0);
    Position hillPos = new Position(0,1);
    Position mountainPos = new Position(2,2);

    //For units: troop layout
    Position archerPos = new Position(2,0);
    Position legionPos = new Position(3,2);
    Position settlerPos = new Position(4,3);


    //For cities
    Position redCity = new Position(1,1);
    Position blueCity = new Position(4,1);


    @Override
    public void worldBuild(GameImpl g, HashMap<Position, UnitImpl> unitMap, HashMap<Position, CityImpl> cityMap, HashMap<Position, TileImpl> tileMap) {
        unitMap.put(archerPos, new UnitImpl(archerPos, ARCHER, Player.RED));
        unitMap.put(legionPos, new UnitImpl(legionPos, LEGION, Player.BLUE));
        unitMap.put(settlerPos, new UnitImpl(settlerPos, SETTLER, Player.RED));
        cityMap.put(redCity, new CityImpl(redCity, ARCHER, Player.RED));
        cityMap.put(blueCity, new CityImpl(blueCity, SETTLER, Player.BLUE));
        tileMap.put(oceanPos, new TileImpl(oceanPos, OCEANS));
        tileMap.put(hillPos, new TileImpl(oceanPos, HILLS));
        tileMap.put(mountainPos, new TileImpl(oceanPos, MOUNTAINS));
    }
}
