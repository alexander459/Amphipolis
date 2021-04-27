package model.tiles;
import model.enumerators.*;

/**
 * MosaicTile class represents a tile of type mosaic
 */
public class MosaicTile extends Tile{
    private final MosaicColour Colour;

    public String toString(){
        return super.toString() + ", Colour: " + Colour;
    }
    /**
     * Constructor
     * Creates a new mosaic tile with 'Colour' Colour
     * @param Colour, mosaic's colour
     * Postconditions the new mosaic has been created
     */
    public MosaicTile(MosaicColour Colour){
        super(TileType.MOSAIC);
        this.Colour=Colour;
    }

    /**
     * Accessor returns the colour of the mosaic
     * Postcondition The colour of the mosaic has been returned
     * @return returns the colour of the mosaic
     */
    public MosaicColour getColour(){return this.Colour;}
}
