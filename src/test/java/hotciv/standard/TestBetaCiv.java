package hotciv.standard;

import VersionControl.AlphaVersion;
import VersionControl.BetaVersion;
import hotciv.framework.*;

import org.junit.*;

import static hotciv.framework.GameConstants.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class TestBetaCiv {
    private Game game;

    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new BetaVersion());
    }
}
