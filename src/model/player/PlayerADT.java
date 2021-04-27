package model.player;
import model.characters.*;
import model.characters.Character;
import model.enumerators.*;
import model.exceptions.*;
import model.tiles.*;

public interface PlayerADT {

    /**
     * Accessor returns the players assistant
     * Postconditions the assistant has been returned
     * @return the players assistant
     */
    Assistant getAssistant();

    /**
     * Accessor returns the players archaeologist
     * Postconditions the archaeologist has been returned
     * @return the players archaeologist
     */
    Archeologist getArcheologist();

    /**
     * Accessor returns the players professor
     * Postconditions the professor has been returned
     * @return the players professor
     */
    Professor getProfessor();

    /**
     * Accessor returns the players digger
     * Postconditions the digger has been returned
     * @return the players digger
     */
    Digger getDigger();

    /**
     * Accessor returns the number of the tiles the player is allowed to collect ath his round
     * Postconditions an integer has been returned
     * @return the number of the tiles the player is allowed to collect
     */
    int getTilesToCollect();

    /**
     * Accessor returns the number of tiles the player has collected at his round
     * Postconditions an integer has been returned
     * @return the number of tiles the player has collected at his round
     */
    int getHasCollected();

    /**
     * Accessor returns a tile type that shows the player's chosen area
     * Postconditions a tile type has been returned
     * @return a tile type that shows the player's chosen area
     */
    TileType getChosenArea();

    /**
     * Accessor returns the players total points
     * Postconditions an integer that represents the player's total points has been returned
     * @return the players total points
     */
    int getTotalPoints();

    /**
     * Accessor returns the last used character
     * Postconditions A Character has been returned
     * @return the last used character
     */
    CharacterType getLastUsedCharacter();

    /**
     * Accessor returns the number of the statues the player has collected in an array
     * Postconditions An array has been returned
     * @return the number of the statues the player has collected in an array
     */
    int[] getNumberOfStatues();

    /**
     * Transformer sets the player's last used character 'type'
     * Postconditions the lastUsedCharacter has been set
     * @param type the type of the last used character
     */
    void setLastUsedCharacter(CharacterType type);

    /**
     * Transformer sets the number of the tiles the player can collect in round
     * Postconditions the number of the tiles has been set
     * @param tilesToCollect the number of the tiles the player can collect
     */
    void setTilesToCollect(int tilesToCollect);

    /**
     * Transformer sets the player's total points
     * postconditions the total points have been set
     */
    void setTotalPoints();

    /**
     * Transformer sets the player's points gained from caryatid
     * Postconditions the caryatid points have been set
     * @param points the caryatid points
     */
    void setCaryatidPoints(int points);

    /**
     * Transformer sets the player's points gained from sphinx
     * Postconditions the sphinx points have been set
     * @param points the sphinx points
     */
    void setSphinxPoints(int points);

    /**
     * Transformer Takes a tile and place it at the players collection
     * postconditions the tile has been collected by the player
     * @param tile the tile to be collected
     */
    void CollectTile(Tile tile);

    /**
     * Transformer Sets the players turn as true
     * Postconditions the players turn boolean 'MyTurn' has been set as true
     */
    void Play();

    /**
     * Transformer sets players turn 'MyTurn' false and OneCharacterHasBeenUsed false
     * Posconditions the players turns and OneCharacterHasBeenUsed have been set as false
     */
    void FinishTurn();

    /**
     * Transformer lets the player to chose an area to collect 2 tiles
     * @param ChosenArea the area to collect the first 2 tiles
     */
    void SetChosenArea(TileType ChosenArea);

    /**
     * Accessor returns the players id
     * Postconditions the players id has been returned
     * @return the players id
     */
    String getPlayersId();

    /**
     * Transformer puts a mosaic tile at players Collection
     * Postcondition a mosaic tile has been put at the players Collection
     * @param mosaicTile the mosaic tile to be added at the players Collection
     */
    void CollectMosaic(MosaicTile mosaicTile) ;

    /**
     * Transformer puts an amphora tile at players Collection
     * Postcondition a amphora tile has been put at the players Collection
     * @param amphoraTile the amphora tile to be added at the players Collection
     */
    void CollectAmphora(AmphoraTile amphoraTile) ;

    /**
     * Transformer puts a statue tile at players Collection
     * Postcondition a statue tile has been put at the players Collection
     * @param statueTile the statue tile to be added at the players Collection
     */
    void CollectStatue(StatueTile statueTile) ;

    /**
     * Transformer puts a skeleton tile at players Collection
     * Postcondition a skeleton tile has been put at the players Collection
     * @param skeletonTile the skeleton tile to be added at the players Collection
     */
    void CollectSkeleton(SkeletonTile skeletonTile);

    /**
     * Transformer sets the amphMosSkelPoints variable with the sum of the points
     * gained from the amphoras, the mosaics and the skeleton. This method can be called only once
     */
    void setAmpMosSkelPoints();

    /**
     * Observer returns weather the player can use a character at his round or not
     * Postcondition a boolean has been returned
     * @return true if the player can use a character at his round and false if not
     */
    boolean CanUseCharacter();

    /**
     * Transformer lets the player to use a character
     * Preconditions the character must not be used
     * @param character the character to be used by the player
     * @throws CharacterAlreadyUsedException the character is used
     */
    void useCharacter(CharacterType character) throws CharacterAlreadyUsedException;
}