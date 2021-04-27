package model.tiles;

import model.enumerators.*;

/**
 * Amphora class represents a tile of type amphora
 */
public class AmphoraTile extends Tile{
    private final AmphoraColour Colour;

    public String toString(){
        return super.toString() + ", Colour: " + Colour;
    }
    /**
     * Constructor
     * Creates a new amphora with 'Colour' Colour
     * @param Colour, amphora's colour
     * Postconditions the new mosaic has been created
     */
    public AmphoraTile(AmphoraColour Colour){
        super(TileType.AMPHORA);
        this.Colour=Colour;
    }

    /**
     * Accessor returns the colour of the amphora
     * Postconditions the colour of the amphora has been returned
     * @return returns the colour of the amphora
     */
    public AmphoraColour getColour(){return this.Colour;}
}
