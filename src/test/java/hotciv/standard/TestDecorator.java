package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.GammaVersion;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestDecorator {
    private GameImpl gameImpl;
    private GameDecorator gameDec;
    private UnitImpl unit;

    @Before
    public void setUp() {
        gameImpl = new GameImpl(new GammaVersion());
        gameDec = new GameDecorator(gameImpl);
    }


    @Test
    public void testPrintGetWinner() {
        for(int i=0; i<100; i++) {
            gameImpl.endOfTurn();
        }
        gameDec.getWinner();
    }
    @Test
    public void testPrintGetPlayerInTurn() {
        for(int i=0; i<100; i++) {
            gameImpl.endOfTurn();
        }
        gameDec.getPlayerInTurn();
    }
    @Test
    public void testPrintGetAge() {
        for(int i=0; i<=72; i++) {
            gameImpl.endOfTurn();
        }
        gameDec.getAge();
    }
    @Test
    public void testMoveUnitAble() {
        gameDec.moveUnit(new Position(3,2), new Position(4,3));
    }
//    @Test
//    public void testMoveUnitUnable() {
//        gameDec.moveUnit(new Position(3,2), new Position(3,2));
//    }
    @Test
    public void testEndOfTurn() {
        gameDec.endOfTurn();
    }
//    @Test
//    public void testChangeWorkForceFocusInCityAt() {
//        gameDec.changeWorkForceFocusInCityAt(new Position(3,2), );
//    }
    @Test
    public void changeProductionInCityAt() {
        gameDec.changeProductionInCityAt(new Position(4,1), LEGION);
    }
    @Test
    public void performUnitActionAt() {
        gameDec.performUnitActionAt(new Position(3,6));
    }
}
