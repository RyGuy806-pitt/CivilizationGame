package Strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class BetaWinner implements Winner{

    @Override
    public Player calculateWinner(GameImpl g) {
        return Player.BLUE;
    }
}
