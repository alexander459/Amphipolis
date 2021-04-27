package model.characters;
import model.enumerators.*;

/**
 * This class represents a character of "Archeologist" type
 */
public class Archeologist extends Character{

    /**
     * Constructor
     * creates a new character of type archeologists with his attributes
     * Postconditions an Archeologist has been created
     */
    public Archeologist(){
        super(CharacterType.ARCHEOLOGIST, 2);
    }
}
