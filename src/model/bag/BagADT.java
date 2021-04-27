package model.bag;
import model.exceptions.*;
import model.tiles.*;

public interface BagADT {

    /**
     * Accessor returns the number of the available tiles in the bag
     * Postconditions the number of the tiles in the bag has been returned
     * returns the number of available tiles which are in the bug
     * @return the number of available tiles which are in the bug
     */
    int getTilesInBag();

    /**
     * Observer returns weather the bag is empty or not
     * Postconditions a boolean haw been returned
     * @return returns true if bag is empty, false if not
     */
    boolean IsEmptyBag();

    /**
     * Transformer Pops a random tile from the bag to use it
     * Preconditions the bag must not be empty
     * Postconditions a random tile has been returned
     * @return returns a random tile from the bag
     * @throws BagEmptyException bag must not be empty
     */
    Tile PopFromBag() throws BagEmptyException;

    /**
     * Accessor returns a random amphora
     * @return a random amphora
     * Precondition the bag must contain amphora
     * Postconditions a random amphora has been returned
     * @throws TileDoesNotExistException bag must contain amphora
     */
    AmphoraTile getAmphora() throws TileDoesNotExistException;

    /**
     * Accessor returns a random mosaic
     * @return a random mosaic
     * Precondition the bag must contain mosaic
     * Postconditions a random mosaic has been returned
     * @throws TileDoesNotExistException bag must contain mosaic
     */
    MosaicTile getMosaic() throws TileDoesNotExistException;

    /**
     * Accessor returns a random statue
     * @return a random statue
     * Precondition the bag must contain statue
     * Postconditions a random statue has been returned
     * @throws TileDoesNotExistException bag must contain statue
     */
    StatueTile getStatue() throws TileDoesNotExistException;

    /**
     * Accessor returns a random skeleton
     * @return a random skeleton
     * Precondition the bag must contain skeleton
     * Postconditions a random skeleton has been returned
     * @throws TileDoesNotExistException bag must contain skeleton
     */
    SkeletonTile getSkeleton() throws TileDoesNotExistException;
}