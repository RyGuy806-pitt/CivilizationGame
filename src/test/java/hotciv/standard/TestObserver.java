package hotciv.standard;

import VersionControl.GammaVersion;
import VersionControl.SemiVersion;
import hotciv.framework.GameObserver;
import hotciv.framework.Position;
import hotciv.spy.GameObserverSpy;
import org.junit.Before;
import org.junit.Test;

import static hotciv.framework.GameConstants.SETTLER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestObserver {
    private GameImpl gameImpl;
    private GameObserver gameOb;
    private UnitImpl unit;

    @Before
    public void setUp() {
        gameImpl = new GameImpl(new SemiVersion());
    }

    @Test
    public void EndOfTurnObserver(){
        gameImpl.addObserver(new GameObserverSpy());
        gameImpl.endOfTurn();
        gameImpl.endOfTurn();
    }

    @Test
    public void WorldChangeObserver(){
        gameImpl.addObserver(new GameObserverSpy());
        gameImpl.endOfTurn();
        gameImpl.endOfTurn();
        gameImpl.moveUnit(new Position(4,3), new Position(3,3));
    }

    @Test
    public void TileFocusObserver(){
        gameImpl.addObserver(new GameObserverSpy());
        gameImpl.endOfTurn();
        gameImpl.endOfTurn();
        gameImpl.changeProductionInCityAt(new Position(8,12), SETTLER);
    }


}
