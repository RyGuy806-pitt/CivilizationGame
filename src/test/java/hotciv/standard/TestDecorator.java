package hotciv.standard;

import VersionControl.AlphaVersion;
import hotciv.framework.Game;
import hotciv.framework.Player;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestDecorator {
    private GameImpl gameImpl;
    private GameDecorator gameDec;

    @Before
    public void setUp() {
        gameImpl = new GameImpl(new AlphaVersion());
        gameDec = new GameDecorator(gameImpl);
    }


    @Test
    public void testPrintGetWinner() {
        for(int i=0; i<100; i++) {
            gameImpl.endOfTurn();
        }
        assertThat(gameDec.getWinner(), is(Player.RED));
    }
    @Test
    public void testPrintGetPlayerInTurn() {
        for(int i=0; i<100; i++) {
            gameImpl.endOfTurn();
        }
        assertThat(gameDec.getPlayerInTurn(), is(Player.RED));
    }
}
