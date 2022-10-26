package Strategies;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public interface Winner {
    public Player calculateWinner(GameImpl g);
}
