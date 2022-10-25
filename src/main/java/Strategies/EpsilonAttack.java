package Strategies;

import hotciv.framework.Game;

import hotciv.framework.Position;

public class EpsilonAttack implements Attack{

    @Override
    public boolean attack(Game game, Position to, Position from) {
        return false;
    }
}
