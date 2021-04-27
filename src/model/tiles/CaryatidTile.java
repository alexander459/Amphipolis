package model.tiles;
import model.enumerators.*;

/**
 * CaryatidTile class represents a statue of type caryatid
 */
public class CaryatidTile extends StatueTile{

    public String toString(){
        return super.toString() + ", Statue Type: Caryatid";
    }
    /**
     * Constructor
     * creates a new statue of type caryatid
     * Postconditions a new statue of type caryatid has been created
     */
    public CaryatidTile(){
        super(StatueType.CARYATID);
    }
}
