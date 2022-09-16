package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

public class TileImpl implements Tile {

    private Position position;
    private String type;

    public TileImpl(Position p, String t){
        this.position = p;
        this.type = t;

    }
    public Position getPosition(){
        return this.position;
    }

    @Override
    public String getTypeString() {
        return this.type;
    }

}
