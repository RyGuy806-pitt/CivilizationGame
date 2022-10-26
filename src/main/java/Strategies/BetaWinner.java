package Strategies;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

public class BetaWinner implements Winner{

    @Override
    public Player calculateWinner(GameImpl g) {
        if(g.getCityAt(new Position(4, 1)).getOwner() == g.getCityAt(new Position(1, 1)).getOwner()){
            return g.getCityAt(new Position(1,1)).getOwner();
        }
        return null;
    }
}
