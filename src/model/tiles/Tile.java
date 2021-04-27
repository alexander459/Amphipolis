package model.tiles;

import model.enumerators.*;

/**
 * Tile class represents a tile
 */
public class Tile {
    private final TileType Type;

    /**
     * Constructor Sets the 'Type' type
     * @param Type the type of the tile. it can be landslide, skeleton, amphora, statue or mosaic
     * Postconditions the type hsa been set as type
     */
    public Tile(TileType Type){this.Type=Type;}

    /**
     * Accessor returns the type of the tile
     * @return the type of the tile
     * Postconditions the type of the tile has been returned
     */
    public TileType getType(){return this.Type;}

    public String toString(){
        return "Type: " + Type;
    }
}