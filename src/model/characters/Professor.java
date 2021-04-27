package model.characters;
import model.enumerators.*;

/**
 * This class represents a character of "Professor" type
 */
public class Professor extends Character{

    /**
     * Constructor
     * creates a new character of type Professor with his attributes
     * Postconditions an Professor has been created
     */
    public Professor(){
        super(CharacterType.PROFESSOR, 4);
    }
}
