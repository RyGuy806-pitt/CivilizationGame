package Strategies;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class DeltaWorldMap implements WorldMap{
    Position oceanPos = new Position(1,0);
    Position hillPos = new Position(0,1);
    Position mountainPos = new Position(2,2);

    Position redCity = new Position(8,12);
    Position blueCity = new Position(4,5);


    @Override
    public void worldBuild(GameImpl g, HashMap<Position, UnitImpl> unitMap, HashMap<Position, CityImpl> cityMap, HashMap<Position, TileImpl> tileMap) {
        cityMap.put(redCity, new CityImpl(redCity, ARCHER, Player.RED));
        cityMap.put(blueCity, new CityImpl(blueCity, SETTLER, Player.BLUE));
        tileMap.put(oceanPos, new TileImpl(oceanPos, OCEANS));
        tileMap.put(hillPos, new TileImpl(oceanPos, HILLS));
        tileMap.put(mountainPos, new TileImpl(oceanPos, MOUNTAINS));
    }
}
