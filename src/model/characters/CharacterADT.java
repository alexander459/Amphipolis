package model.characters;
import model.player.Player;
import model.exceptions.*;
import model.enumerators.*;

public interface CharacterADT {
    /**
     * Accessor returns characters type
     * Postcondition the characters type has been returned
     * @return characters type
     */
    CharacterType getType();

    /**
     * Transformer sets the 'player' player
     * Postonditions the 'player' has been set as player
     * @param player the player holder of the character
     */
    void setOwner(Player player);

    /**
     * Accessor returns the owner of the character
     * Postcondition the owner of the character has been returned
     * @return the owner of the character
     */
    Player getOwner();

    /**
     * Accessor returns the number of the tiles this character is allowed to collect
     * Postcondition the number of the tiles has been returned
     * @return the number of the tiles this character is allowed to collect
     */
    int getTilesToCollect();

    /**
     * Transformer Sets the HaveBeenUsed field as true when a character is used by the player
     * Precondition The character must be unused
     * Postcondition The character will be seted as used
     * @throws CharacterAlreadyUsedException if the character have been used
     */
    void Use() throws CharacterAlreadyUsedException;

    /**
     * Observer returns whether the player have been used or not
     * Postconditions a boolean that show if the character is used will be returned
     * @return a boolean that shows weather the character has been used from the player or not
     */
    boolean isUsed();
}