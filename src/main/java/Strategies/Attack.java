package Strategies;

import hotciv.framework.Game;

import javax.swing.text.Position;

public interface Attack {
    public boolean attack(Game game, Position to, Position from);
}
