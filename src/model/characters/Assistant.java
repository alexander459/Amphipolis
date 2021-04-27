package model.characters;
import model.enumerators.*;

/**
 * This class represents a character of "Assistant" type
 */
public class Assistant extends Character{

    /**
     * Constructor
     * creates a new character of type Assistant with his attributes
     * Postconditions an Assistant has been created
     */
    public Assistant() {
        super(CharacterType.ASSISTANT, 1);
    }
}
