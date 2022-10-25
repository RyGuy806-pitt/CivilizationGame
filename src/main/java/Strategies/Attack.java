package Strategies;

import hotciv.framework.Game;
import hotciv.framework.Position;


public interface Attack {
    public boolean attack(Game game, Position to, Position from);
}
