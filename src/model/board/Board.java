package model.board;
import model.bag.Bag;
import model.characters.Character;
import model.characters.Digger;
import model.enumerators.TileType;
import model.exceptions.*;
import model.player.Player;
import model.tiles.*;
import java.util.ArrayList;
import java.util.Random;
import model.characters.*;
/**
 * class Board represents the board of the game
 */
public class Board implements BoardADT{
    private final AmphoraPlace amphoraPlace = new AmphoraPlace();
    private final MosaicPlace mosaicPlace = new MosaicPlace();
    private final SkeletonPlace skeletonPlace = new SkeletonPlace();
    private final StatuePlace statuePlace = new StatuePlace();
    private final Gate gate = new Gate();
    private final Bag bag = new Bag();
    private final ArrayList<Player> players = new ArrayList<>();
    private final Tile[] roundCollectedTiles = new Tile[4];
    private boolean finished = false;


    /**
     * Constructor creates a new board and initializes it
     * Postcondition the new board has been created initialized
     * Preconditions the bag must contain at least one tile of every type
     * @throws TileDoesNotExistException the bag must contains at least one amphora,
     *                                   one mosaic, one skeleton and one statue
     */
    public Board() throws TileDoesNotExistException{
        Tile temp;
        temp=bag.getAmphora();
        roundCollectedTiles[0]=temp;
        amphoraPlace.addAmphoraTile((AmphoraTile)temp);

        temp=bag.getMosaic();
        roundCollectedTiles[1]=temp;
        mosaicPlace.addMosaicTile((MosaicTile)temp);

        temp=bag.getStatue();
        roundCollectedTiles[2]=temp;
        statuePlace.addStatueTile((StatueTile)temp);

        temp=bag.getSkeleton();
        roundCollectedTiles[3]=temp;
        skeletonPlace.addSkeletonTile((SkeletonTile)temp);
    }

    /**
     * Accessor returns the amphora tiles of the amphora place
     * Postconditions an integer has been returned
     * @return the number amphora tiles of the amphora place
     */
    @Override
    public int getAmphoraTilesOnBoard(){return amphoraPlace.getNumberOfTiles();}

    /**
     * Accessor returns the mosaic tiles of the mosaic place
     * Postconditions an integer has been returned
     * @return the number of the mosaic tiles on the mosaic place
     */
    @Override
    public int getMosaicTilesOnBoard(){return mosaicPlace.getNumberOfTiles();}

    /**
     * Accessor returns the skeletons tiles of the skeleton place
     * Postconditions an integer has been returned
     * @return the number of the skeleton tiles on the skeleton place
     */
    @Override
    public int getSkeletonTilesOnBoard(){return skeletonPlace.getNumberOfTiles();}

    /**
     * Accessor returns the statue tiles of the statue place
     * Postconditions an integer has been returned
     * @return the number of statue tiles on the statue place
     */
    @Override
    public int getStatueTilesOnBoard(){return statuePlace.getNumberOfTiles();}

    /**
     * Accessor returns an array with the drawn tiles of every round
     * Postconditions a Tile array has been returned
     * @return an array with the drawn tiles of every round
     */
    @Override
    public Tile[] getRoundCollectedTiles(){return this.roundCollectedTiles;}

    /**
     * Transformer adds a new player at the
     * Precondition no more than 4 players allowed
     * Postconditions the new player has been added at the players list
     * @param player the player to add at the game
     * @throws MoreThanFourPlayersException there are already four players, game is full
     */
    @Override
    public void addPlayer(Player player) throws MoreThanFourPlayersException{
        if(players.size()==4)
            throw new MoreThanFourPlayersException();
        players.add(player);
    }

    /**
     * Accessor returns the player with index 'index'
     * @param index The index of the player
     * @return the player with index 'index'
     */
    @Override
    public Player getPlayer(int index){
        return players.get(index);
    }

    /**
     * Transformer adds a tile from the bag on the board
     * Postconditions a tile has been added
     * @param tile the tile to be added on the board
     */
    @Override
    public void addTileOnBoard(Tile tile) throws GateClosedException{
        switch (tile.getType()) {
            case AMPHORA:
                amphoraPlace.addAmphoraTile((AmphoraTile) tile);
                break;
            case MOSAIC:
                mosaicPlace.addMosaicTile((MosaicTile) tile);
                break;
            case STATUE:
                statuePlace.addStatueTile((StatueTile) tile);
                break;
            case SKELETON:
                skeletonPlace.addSkeletonTile((SkeletonTile) tile);
                break;
            case LANDSLIDE:
                gate.addLandSlide((LandSlideTile) tile);
                break;
        }
    }

    /**
     * Accessor pops the 'tile' tile from the board
     * Postcondition the tile has been popped and returned
     * Preconditions the tile must exist on the board
     * @throws TileDoesNotExistException if  the tile does not exist
     * @return the popped tile
     */
    @Override
    public Tile popTileFromBoard(Tile tile) throws TileDoesNotExistException{
        Tile temp;
        switch (tile.getType()) {
            case AMPHORA:
                temp = amphoraPlace.PopFromAmphoraPlace((AmphoraTile) tile);
                break;
            case MOSAIC:
                temp=mosaicPlace.PopFromMosaicPlace((MosaicTile) tile);
                break;
            case STATUE:
                temp=statuePlace.PopFromStatuePlace((StatueTile) tile);
                break;
            case SKELETON:
                temp=skeletonPlace.PopFromSkeletonPlace((SkeletonTile) tile);
                break;
            default:
                return null;
        }
        return temp;
    }

    /**
     * Transformer draws 4 tiles from the bag and place them at their place
     * Postconditions the tiles have been drawn
     * Preconditions The bag must not be empty
     * Preconditions the gate must be opened
     * @throws BagEmptyException if the bag is empty
     * @throws GateClosedException if the gate is closed
     */
    @Override
    public void drawTiles() throws BagEmptyException, GateClosedException{
        Tile temp;
        for(int i=0; i<4; i++){
            temp=bag.PopFromBag();
            roundCollectedTiles[i]=temp;
            this.addTileOnBoard(temp);
        }
        if(gate.isGateFull())
            finished=true;
    }

    /**
     * Observer returns true if the game has finished
     * Postconditions a boolean has been returned
     * @return true if the game has finished or false if not
     */
    @Override
    public boolean hasFinished(){
        return finished;
    }
}
