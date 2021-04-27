package model.board;

import model.exceptions.*;
import model.player.Player;
import model.tiles.Tile;

public interface BoardADT {

    /**
     * Accessor returns the amphora tiles of the amphora place
     * Postconditions an integer has been returned
     * @return the number amphora tiles of the amphora place
     */
    int getAmphoraTilesOnBoard();

    /**
     * Accessor returns the mosaic tiles of the mosaic place
     * Postconditions an integer has been returned
     * @return the number of the mosaic tiles on the mosaic place
     */
    int getMosaicTilesOnBoard();

    /**
     * Accessor returns the skeletons tiles of the skeleton place
     * Postconditions an integer has been returned
     * @return the number of the skeleton tiles on the skeleton place
     */
    int getSkeletonTilesOnBoard();

    /**
     * Accessor returns the statue tiles of the statue place
     * Postconditions an integer has been returned
     * @return the number of statue tiles on the statue place
     */
    int getStatueTilesOnBoard();

    /**
     * Accessor returns an array with the drawn tiles of every round
     * Postconditions a Tile array has been returned
     * @return an array with the drawn tiles of every round
     */
    Tile[] getRoundCollectedTiles();

    /**
     * Transformer adds a new player at the
     * Precondition no more than 4 players allowed
     * Postconditions the new player has been added at the players list
     * @param player the player to add at the game
     * @throws MoreThanFourPlayersException there are already four players, game is full
     */
    void addPlayer(Player player) throws MoreThanFourPlayersException;

    /**
     * Accessor returns the player with index 'index'
     * @param index The index of the player
     * @return the player with index 'index'
     */
    Player getPlayer(int index);

    /**
     * Transformer adds a tile from the bag on the board
     * Postconditions a tile has been added
     * @param tile the tile to be added on the board
     */
    void addTileOnBoard(Tile tile) throws GateClosedException;

    /**
     * Accessor pops the 'tile' tile from the board
     * Postcondition the tile has been popped and returned
     * Preconditions the tile must exist on the board
     * @throws TileDoesNotExistException if  the tile does not exist
     * @return the popped tile
     */
    Tile popTileFromBoard(Tile tile) throws TileDoesNotExistException;

    /**
     * Transformer draws 4 tiles from the bag and place them at their place
     * Postconditions the tiles have been drawn
     * Preconditions The bag must not be empty
     * @throws BagEmptyException if the bag is empty
     */
    void drawTiles() throws BagEmptyException, GateClosedException;

    /**
     * Observer returns true if the game has finished
     * @return true if the game has finished or false if not
     */
    boolean hasFinished();
}
