package Strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class AlphaWinner implements Winner{
    @Override
    public Player calculateWinner(GameImpl g) {
        if (g.getAge() >= 3000) {
            return Player.RED;
        }
        return null;
    }
}
