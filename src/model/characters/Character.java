package model.characters;
import model.player.Player;
import model.exceptions.*;
import model.enumerators.*;

/**
 * This class represents one of the four characters
 */
public class Character implements CharacterADT{
    private final CharacterType Type;
    private final int TilesToCollect;
    private boolean HasBeenUSed = false;
    private Player owner;

    /**
     * Constructor
     * Postcondition Creates a new character with 'type' type, 'TilesToCollect' tiles,
     *               'CollectFromArea' CollectFromArea, 'CollectOnlyFromArea' CollectOnlyFromArea
     * @param Type The type of the character
     * @param TilesToCollect The number of the tiles this character is allowed to collect
     */
    public Character(CharacterType Type, int TilesToCollect){
        this.Type=Type;
        this.TilesToCollect=TilesToCollect;
    }

    /**
     * Transformer sets the owner of the character
     * Postonditions the 'owner' has been set as owner
     * @param owner the player holder of the character
     */
    @Override
    public void setOwner(Player owner){
        this.owner=owner;
    }

    /**
     * Accessor returns the owner of the character
     * Postcondition the owner of the character has been returned
     * @return the owner of the character
     */
    @Override
    public Player getOwner(){
        return this.owner;
    }

    /**
     * Accessor returns characters type
     * Postcondition the characters type has been returned
     * @return characters type
     */
    @Override
    public CharacterType getType(){
        return this.Type;
    }

    /**
     * Accessor returns the number of the tiles this character is allowed to collect
     * Postcondition the number of the tiles has been returned
     * @return the number of the tiles this character is allowed to collect
     */
    @Override
    public int getTilesToCollect(){
        return this.TilesToCollect;
    }

    /**
     * Transformer Sets the HaveBeenUsed field as true when a character is used by the player
     * Precondition The character must be unused
     * Postcondition The character will be seted as used
     * @throws CharacterAlreadyUsedException if the character have been used
     */
    @Override
    public void Use() throws CharacterAlreadyUsedException{
        if(this.isUsed())
            throw new CharacterAlreadyUsedException();
        this.HasBeenUSed=true;
        owner.setTilesToCollect(TilesToCollect);
        owner.setLastUsedCharacter(Type);
    }

    /**
     * Observer returns whether the player have been used or not
     * Postconditions a boolean that show if the character is used will be returned
     * @return a boolean that shows weather the character has been used from the player or not
     */
    @Override
    public boolean isUsed(){
        return this.HasBeenUSed;
    }
}