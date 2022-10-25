package Strategies;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;

public class ZetaWinner implements Winner{
    Winner Epsilon, Beta, State;
    public ZetaWinner(Winner Beta, Winner Epsilon){
        this.Beta = Beta;
        this.Epsilon = Epsilon;
        this.State = null;
    }
    @Override
    public Player calculateWinner(GameImpl g) {
        if(g.getAge() < -2000){
            g.setBlueWins(0);
            g.setRedWins(0);
            State = Beta;
            //keep battles won at 0 cause its only relevant in Epsilon
        }
        else {
            State = Epsilon;
        }
        return State.calculateWinner(g);
    }
}
