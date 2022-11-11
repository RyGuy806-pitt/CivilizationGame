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
        gameDec = new GameDecorator(gameImpl, false);
    }

    @Test
    public void testGame1() {
        gameDec.toggleTranscriptOn();
        for(int i=0; i<100; i++) {
            gameImpl.endOfTurn();
        }
        gameDec.getAge();
        gameDec.moveUnit(new Position(3,2), new Position(3,2));
        gameDec.getPlayerInTurn();
        gameDec.endOfTurn();
        gameDec.moveUnit(new Position(3,2), new Position(4,3));
        gameDec.performUnitActionAt(new Position(4,1));
        gameDec.toggleTranscriptOff();
        gameDec.changeProductionInCityAt(new Position(4,1), LEGION);
        gameDec.getWinner();
        gameDec.endOfTurn();
        gameDec.getWinner();
        //gameDec.changeWorkForceFocusInCityAt(new Position(3,2), );
    }

}
