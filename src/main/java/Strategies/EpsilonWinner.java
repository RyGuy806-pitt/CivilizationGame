package Strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class EpsilonWinner implements Winner{
    @Override
    public Player calculateWinner(GameImpl g) {
        if(g.getBlueWins() == 3){
            return Player.BLUE;
        } else if (g.getRedWins()== 3) {
            return Player.RED;
        } else {
            return null;
        }
    }
}
