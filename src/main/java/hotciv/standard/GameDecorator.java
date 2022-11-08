package hotciv.standard;

import hotciv.framework.*;

public class GameDecorator implements Game {
    private GameImpl game;

    public GameDecorator(GameImpl gameImpl) {
        this.game = gameImpl;
    }
    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        Player player = game.getPlayerInTurn();
        System.out.println("Player in turn is " + player + ".");
        return player;
    }

    @Override
    public Player getWinner() {
        Player winner = game.getWinner();
        System.out.println("Winner is " + winner + ".");
        return winner;
    }

    @Override
    public int getAge() {
        System.out.println("World age is " + game.getAge());
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        if(game.moveUnit(from, to)) {
            System.out.println("Unit moved to " + to + " from " + from + ".");
        }
        else {
            System.out.println("Unit not able to move.");
        }
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        System.out.println("Turn ended.");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        System.out.println("Work Force is" + balance + " in City at " + p + ".");
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println("Production at City " + p + " is " + unitType + ".");
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        System.out.println("Unit action performed at " + p + ".");
        game.performUnitActionAt(p);
    }
}
