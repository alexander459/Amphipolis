package model.player;
import model.tiles.*;

public interface PlayersCollectionADT {

    /**
     * Transformer puts a mosaic tile at players MosaicArray
     * Postcondition a mosaic tile has been put at the players MosaicArray
     * @param mosaicTile the mosaic tile to be added at the players MosaicArray
     */
    void addMosaic(MosaicTile mosaicTile);

    /**
     * Transformer puts an amphora tile at players AmphoraArray
     * Postcondition a amphora tile has been put at the players AmphoraArray
     * @param amphoraTile the amphora tile to be added at the players AmphoraArray
     */
    void addAmphora(AmphoraTile amphoraTile);

    /**
     * Transformer puts a statue tile at players StatueArray
     * Postcondition a statue tile has been put at the players StatueArray
     * @param statueTile the statue tile to be added at the players StatueArray
     */
    void addStatue(StatueTile statueTile);

    /**
     * Transformer puts a skeleton tile at players SkeletonArray
     * Postcondition a skeleton tile has been put at the players SkeletonArray
     * @param skeletonTile the skeleton tile to be added at the players SkeletonArray
     */
    void addSkeleton(SkeletonTile skeletonTile);

    /**
     * Accessor calculates and returns the total points collected from the mosaics tiles
     * Postconditions the points have been calculated and returned
     * @return the total points collected from the mosaics tiles
     */
    int getMosaicPoints();

    /**
     * Accessor calculates and returns the total points collected from the amphora tiles
     * Postconditions the points have been calculated and returned
     * @return the total points collected from the amphora tiles
     */
    int getAmphoraPoints();

    /**
     * Transformer sets the number of caryatid and the number of the sphinx
     * Postconditions the number of the collected statued has been set
     */
    void setNumberOfStatues();

    /**
     * Accessor calculates and returns the total points collected from the skeleton tiles
     * Postconditions the points have been calculated and returned
     * @return the total points collected from the skeleton tiles
     */
    int getSkeletonPoints();
}