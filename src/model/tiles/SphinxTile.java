package model.tiles;
import model.enumerators.*;

/**
 * SphinxTile class represents a statue of type sphinx
 */
public class SphinxTile extends StatueTile{
    public String toString(){
        return super.toString() + ", Statue Type: Sphinx";
    }
    /**
     * Constructor
     * Creates a new Statue of type sphinx
     * Postconditions teh statue type has been seted
     */
    public SphinxTile(){
        super(StatueType.SPHINX);
    }
}
