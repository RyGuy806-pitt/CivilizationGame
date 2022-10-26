package Strategies;

import hotciv.framework.Game;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.Utility;

import java.util.Iterator;
import java.util.Random;

import static hotciv.framework.GameConstants.HILLS;
import static hotciv.framework.GameConstants.FOREST;

public class EpsilonAttack implements Attack{

    @Override
    public boolean attack(Game game, Position to, Position from) {
        //false will mean DefensiveStrength > OffensiveStrength
        //true allows the unit to move, and will destroy the other unit
        //false will need to destroy the attacking unit
        boolean ret = true;
        if((game.getUnitAt(to).getTypeString() != "nothing") && (game.getUnitAt(from).getTypeString() != "nothing")){
            if(totalDefensiveStrength(game, to, game.getUnitAt(to).getOwner()) > totalOffensiveStrength(game, from, game.getUnitAt(from).getOwner())){
               ret = false;
            }
            else{
                ret = true;
            }
        }
        return ret;
    }

    public static int getTerrainFactor(Game game, Position position) {
        // cities overrule underlying terrain
        if ( game.getCityAt(position) != null ) { return 3; }
        Tile t = game.getTileAt(position);
        if ( t.getTypeString() == FOREST ||
                t.getTypeString() == HILLS ) {
            return 2;
        }
        return 1;
    }

    public static int getFriendlySupport(Game game, Position position, Player player) {
        Iterator<Position> neighborhood = Utility.get8neighborhoodIterator(position);
        Position p;
        int support = 0;
        while (neighborhood.hasNext()) {
            p = neighborhood.next();
            if (game.getUnitAt(p) != null &&
                    game.getUnitAt(p).getOwner() == player) {
                support++;
            }
        }
        return support;
    }

    public int totalOffensiveStrength(Game game, Position position, Player player){
        int total = 0;
        int allyBonus = getFriendlySupport(game, position, player);
        int terrainBonus = getTerrainFactor(game, position);
        int attackStrength = game.getUnitAt(position).getAttackingStrength();
        total = (attackStrength + terrainBonus + allyBonus) * rollDice();
        return total;

    }

    public int totalDefensiveStrength(Game game, Position position, Player player){
        int total = 0;
        int allyBonus = getFriendlySupport(game, position, player);
        int terrainBonus = getTerrainFactor(game, position);
        int defensiveStrength = game.getUnitAt(position).getAttackingStrength();
        total = (defensiveStrength + terrainBonus + allyBonus) * rollDice();
        return total;

    }

    public static  int rollDice() {
        Random rand = new Random();
        int random_integer = rand.nextInt(6-1) + 1;
        return random_integer;
    }
}
