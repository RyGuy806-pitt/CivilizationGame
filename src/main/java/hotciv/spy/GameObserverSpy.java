package hotciv.spy;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.ArrayList;
import java.util.List;

public class GameObserverSpy implements GameObserver {

    @Override
    public void worldChangedAt(Position pos) {
        System.out.println("World Changed At Position " + pos);
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        System.out.println("Player " + nextPlayer + "'s turn at Age " + age);
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        System.out.println("Tile focus changed at " + position);
    }


}
