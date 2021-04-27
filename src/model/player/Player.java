package model.player;
import model.characters.*;
import model.characters.Character;
import model.enumerators.*;
import model.tiles.*;
import model.exceptions.*;

public class Player implements PlayerADT{
    private static int numberOfPlayers=0;
    private boolean methodCalled=false;
    private final String PlayersId;
    private int tilesToCollect;
    private int hasCollected;
    private TileType ChosenArea;
    private boolean OneCharacterHasBeenUsed;
    private int totalPoints;
    private int caryatidPoints;
    private int sphinxPoints;
    private int ampMosSkelPoints;
    private final Assistant assistant = new Assistant();
    private final Archeologist archeologist = new Archeologist();
    private final Digger digger = new Digger();
    private final Professor professor = new Professor();
    private final PlayersCollection playersCollection = new PlayersCollection();
    private CharacterType lastUsedCharacter;


    /**
     * Default Constructor sets the players id as 'player <whitespace> <numberOfPlayers>'
     * Creates a new player and initialize some variables
     */
    public Player(){
        numberOfPlayers++;
        OneCharacterHasBeenUsed=false;
        hasCollected=0;
        tilesToCollect=2;
        totalPoints=0;
        this.PlayersId="Player " + numberOfPlayers;
        assignCharacters();
    }

    /**
     * Accessor returns the players assistant
     * Postconditions the assistant has been returned
     * @return the players assistant
     */
    @Override
    public Assistant getAssistant(){return this.assistant;}

    /**
     * Accessor returns the players archaeologist
     * Postconditions the archaeologist has been returned
     * @return the players archaeologist
     */
    @Override
    public Archeologist getArcheologist(){return this.archeologist;}

    /**
     * Accessor returns the players professor
     * Postconditions the professor has been returned
     * @return the players professor
     */
    @Override
    public Professor getProfessor(){return this.professor;}

    /**
     * Accessor returns the players digger
     * Postconditions the digger has been returned
     * @return the players digger
     */
    @Override
    public Digger getDigger(){return this.digger;}

    /**
     * Accessor returns the number of the tiles the player is allowed to collect ath his round
     * Postconditions an integer has been returned
     * @return the number of the tiles the player is allowed to collect
     */
    @Override
    public int getTilesToCollect(){
        return this.tilesToCollect;
    }

    /**
     * Accessor returns the number of tiles the player has collected at his round
     * Postconditions an integer has been returned
     * @return the number of tiles the player has collected at his round
     */
    @Override
    public int getHasCollected(){return this.hasCollected;}

    /**
     * Accessor returns a tile type that shows the player's chosen area
     * Postconditions a tile type has been returned
     * @return a tile type that shows the player's chosen area
     */
    @Override
    public TileType getChosenArea(){return this.ChosenArea;}

    /**
     * Accessor returns the players total points
     * Postconditions an integer that represents the player's total points has been returned
     * @return the players total points
     */
    @Override
    public int getTotalPoints(){return this.totalPoints;}

    /**
     * Accessor returns the last used character
     * Postconditions A Character has been returned
     * @return the last used character
     */
    @Override
    public CharacterType getLastUsedCharacter(){return lastUsedCharacter;}

    /**
     * Accessor returns the number of the statues the player has collected in an array
     * Postconditions An array has been returned
     * @return the number of the statues the player has collected in an array
     */
    @Override
    public int[] getNumberOfStatues(){
        int[] numberOfStatues=new int[2];
        numberOfStatues[0]=playersCollection.getNumberOfCaryatid();
        numberOfStatues[1]=playersCollection.getNumberOfSphinx();
        return numberOfStatues;
    }

    /**
     * Transformer sets the player's last used character 'type'
     * Postconditions the lastUsedCharacter has been set
     * @param type the type of the last used character
     */
    @Override
    public void setLastUsedCharacter(CharacterType type){lastUsedCharacter=type;}

    /**
     * Transformer sets the number of the tiles the player can collect in round
     * Postconditions the number of the tiles has been set
     * @param tilesToCollect the number of the tiles the player can collect
     */
    @Override
    public void setTilesToCollect(int tilesToCollect){this.tilesToCollect=tilesToCollect;}

    /**
     * Transformer sets the player's total points
     * postconditions the total points have been set
     */
    @Override
    public void setTotalPoints(){this.totalPoints=caryatidPoints+sphinxPoints+ampMosSkelPoints;}

    /**
     * Transformer sets the player's points gained from caryatid
     * Postconditions the caryatid points have been set
     * @param points the caryatid points
     */
    @Override
    public void setCaryatidPoints(int points){caryatidPoints=points;}

    /**
     * Transformer sets the player's points gained from sphinx
     * Postconditions the sphinx points have been set
     * @param points the sphinx points
     */
    @Override
    public void setSphinxPoints(int points){sphinxPoints=points;}

    /**
     * Transformer Takes a tile and place it at the players collection
     * postconditions the tile has been collected by the player
     * @param tile the tile to be collected
     */
    @Override
    public void CollectTile(Tile tile){
        switch (tile.getType()){
            case AMPHORA:
                this.CollectAmphora((AmphoraTile) tile);
                break;
            case MOSAIC:
                this.CollectMosaic((MosaicTile) tile);
                break;
            case STATUE:
                this.CollectStatue((StatueTile) tile);
                break;
            case SKELETON:
                this.CollectSkeleton((SkeletonTile) tile);
                break;
        }
        hasCollected++;
    }

    /**
     * Transformer assigns the 4 characters to this player
     * Postconditions the characters have been assigned to the player
     */
    private void assignCharacters(){
        assistant.setOwner(this);
        archeologist.setOwner(this);
        digger.setOwner(this);
        professor.setOwner(this);
    }

    /**
     * Transformer initialize some variables so the player can start his turn
     * Postconditions some variables have been initialized
     */
    @Override
    public void Play(){
        tilesToCollect=2;
        hasCollected=0;
        OneCharacterHasBeenUsed=false;
        ChosenArea=null;
    }

    /**
     * Transformer lets the player to chose an area to collect tiles. Sets the chosenArea of the player
     * @param ChosenArea the area player chose to collect the tiles
     */
    @Override
    public void SetChosenArea(TileType ChosenArea){
        this.ChosenArea=ChosenArea;
    }

    /**
     * Accessor returns the players id
     * Postconditions the players id has been returned
     * @return the players id
     */
    @Override
    public String getPlayersId(){
        return this.PlayersId;
    }

    /**
     * Transformer puts a mosaic tile at players Collection
     * Postcondition a mosaic tile has been put at the players Collection
     * @param mosaicTile the mosaic tile to be added at the players Collection
     */
    @Override
    public void CollectMosaic(MosaicTile mosaicTile) {
        SetChosenArea(TileType.MOSAIC);
        playersCollection.addMosaic(mosaicTile);
    }

    /**
     * Transformer puts an amphora tile at players Collection
     * Postcondition a amphora tile has been put at the players Collection
     * @param amphoraTile the amphora tile to be added at the players Collection
     */
    @Override
    public void CollectAmphora(AmphoraTile amphoraTile) {
        SetChosenArea(TileType.AMPHORA);
        playersCollection.addAmphora(amphoraTile);
    }

    /**
     * Transformer puts a statue tile at players Collection
     * Postcondition a statue tile has been put at the players Collection
     * @param statueTile the statue tile to be added at the players Collection
     */
    @Override
    public void CollectStatue(StatueTile statueTile) {
        SetChosenArea(TileType.STATUE);
        playersCollection.addStatue(statueTile);
    }

    /**
     * Transformer puts a skeleton tile at players Collection
     * Postcondition a skeleton tile has been put at the players Collection
     * @param skeletonTile the skeleton tile to be added at the players Collection
     */
    @Override
    public void CollectSkeleton(SkeletonTile skeletonTile) {
        SetChosenArea(TileType.SKELETON);
        playersCollection.addSkeleton(skeletonTile);
    }

    /**
     * Transformer sets the amphMosSkelPoints variable with the sum of the points
     * gained from the amphoras, the mosaics and the skeleton. This method can be called only once
     */
    @Override
    public void setAmpMosSkelPoints(){
        if(methodCalled)
            return;
        methodCalled=true;
        this.ampMosSkelPoints= playersCollection.getMosaicPoints() + playersCollection.getAmphoraPoints()
                + playersCollection.getSkeletonPoints();
    }

    /**
     * Transformer sets players turn 'MyTurn' false and OneCharacterHasBeenUsed false
     * Posconditions the players turns and OneCharacterHasBeenUsed have been set as false
     */
    @Override
    public void FinishTurn(){
        hasCollected=0;
        OneCharacterHasBeenUsed=false;
    }

    /**
     * Observer returns weather the player can use a character at his round or not
     * Postcondition a boolean has been returned
     * @return true if the player can use a character at his round and false if not
     */
    @Override
    public boolean CanUseCharacter(){
        return !OneCharacterHasBeenUsed;
    }

    /**
     * Transformer lets the player to use a character
     * Preconditions the character must not be used
     * @param character the character to be used by the player
     * @throws CharacterAlreadyUsedException the character is used
     */
    @Override
    public void useCharacter(CharacterType character) throws CharacterAlreadyUsedException{
        if(character==CharacterType.ARCHEOLOGIST) {
            this.archeologist.Use();
            hasCollected=0;
        }else if(character==CharacterType.ASSISTANT){
            this.assistant.Use();
            hasCollected=0;
        }else if(character==CharacterType.PROFESSOR){
            this.professor.Use();
            hasCollected=0;
        }else if(character==CharacterType.DIGGER){
            this.digger.Use();
            hasCollected=0;
        }
        OneCharacterHasBeenUsed=true;
        hasCollected=0;
    }
}