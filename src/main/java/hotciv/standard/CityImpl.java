package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class CityImpl implements City {
    private Position position;
    private String typeProd;
    private Player owner;

    private int population = 1;
    private int production = 6;

    public CityImpl(Position p, String t, Player o){
        this.position = p;
        this.typeProd = t;
        this.owner = o;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public int getSize() {
        return population;
    }

    @Override
    public int getTreasury() {
        return production;
    }

    @Override
    public String getProduction() {
        return typeProd;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
