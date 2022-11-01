package Strategies;

import hotciv.framework.*;

import java.util.*;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

public class DeltaWorldMap implements WorldMap{
    Position redCity = new Position(8,12);
    Position blueCity = new Position(4,5);
    Position settlerPos = new Position(4,3);


    String[] layout =
            new String[] {
                    "...ooMooooo.....",
                    "..ohhoooofffoo..",
                    ".oooooMooo...oo.",
                    ".ooMMMoooo..oooo",
                    "...ofooohhoooo..",
                    ".ofoofooooohhoo.",
                    "...ooo..........",
                    ".ooooo.ooohooM..",
                    ".ooooo.oohooof..",
                    "offfoooo.offoooo",
                    "oooooooo...ooooo",
                    ".ooMMMoooo......",
                    "..ooooooffoooo..",
                    "....ooooooooo...",
                    "..ooohhoo.......",
                    ".....ooooooooo..",
            };


    @Override
    public void worldBuild(GameImpl g, HashMap<Position, UnitImpl> unitMap, HashMap<Position, CityImpl> cityMap, HashMap<Position, TileImpl> tileMap) {
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            String line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                tileMap.put( p, new TileImpl(p, type));
            }
        }
        cityMap.put(redCity, new CityImpl(redCity, ARCHER, Player.RED));
        cityMap.put(blueCity, new CityImpl(blueCity, SETTLER, Player.BLUE));
        unitMap.put(settlerPos, new UnitImpl(settlerPos, SETTLER, Player.RED));
    }
}
