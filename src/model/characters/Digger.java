package model.characters;
import model.enumerators.*;

/**
 * This class represents a character of "Digger" type
 */
public class Digger extends Character{

    /**
     * Constructor
     * creates a new character of type Digger with his attributes
     * Postconditions an Digge has been created
     */
    public Digger(){
        super(CharacterType.DIGGER, 2);
    }
}
