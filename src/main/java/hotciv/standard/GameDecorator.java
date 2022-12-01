package hotciv.standard;

import hotciv.framework.*;

public class GameDecorator implements Game {
    private GameImpl game;
    private boolean toggle;
    public GameDecorator(GameImpl gameImpl, boolean toggle) {
        this.game = gameImpl;
        this.toggle = toggle;
        System.out.print("Game has begun.\n");
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
        if(toggle == true) {
            System.out.println("Winner is " + winner + ".");
        }
        return winner;
    }

    @Override
    public int getAge() {
        if(toggle == true) {
            System.out.println("World age is " + game.getAge() + ".");
        }
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        if(toggle == true) {
            if (game.moveUnit(from, to)) {
                System.out.println("Unit moved from " + from + " to " + to + ".");
            } else {
                System.out.println("Unit not able to move.");
            }
        }
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        if(toggle == true) {
            System.out.println("Turn ended.");
        }
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        if(toggle == true) {
            System.out.println("Work Force is" + balance + " in City at " + p + ".");
        }
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        if(toggle == true) {
            System.out.println("Production at City " + p + " is " + unitType + ".");
        }
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        if(toggle == true) {
            System.out.println("Unit action performed at " + p + ".");
        }
        game.performUnitActionAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }

    public void toggleTranscriptOn() {
        toggle = true;
    }
    public void toggleTranscriptOff() {
        toggle = false;
    }
}
