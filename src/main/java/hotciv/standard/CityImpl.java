package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

import static hotciv.framework.GameConstants.ARCHER;

public class CityImpl implements City {
    private Position position;
    private String typeProd = ARCHER;
    private Player owner;

    private int population = 1;
    private int treasury = 6;

    private int productCost = 4;

    private int treasuryAdd = 6;

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
        return treasury;
    }

    @Override
    public String getProduction() {
        return typeProd;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }


    public void IncrementTreasury(){
        treasury += treasuryAdd;//this is 6, will change later
    }

    public void DecrementTreasury(){
        treasury -= productCost;
    }

    public int getProductCost(){
        return productCost;
    }

    public Position getPosition(){
        return position;
    }


}
