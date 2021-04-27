package model.tiles;

import model.enumerators.*;

/**
 * StatueTile class represents a tile of type Statue
 */
public class StatueTile extends Tile{
    private final StatueType statueType;

    /**
     * Constructor
     * creates a new tile with 'Type' statue and 'statueType' statueType
     * Postconditions the statue has been created
     * @param statueType the type of the statue
     */
    public StatueTile(StatueType statueType){
        super(TileType.STATUE);
        this.statueType=statueType;
    }

    /**
     * Accessor returns the statue type of this statue tile
     * Postconditions the statue type has been returned
     * @return the statue type of the statue
     */
    public StatueType getStatueType(){return this.statueType;}
}
