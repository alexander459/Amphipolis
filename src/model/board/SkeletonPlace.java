package model.board;
import model.tiles.*;
import java.util.ArrayList;
import model.exceptions.*;

public class SkeletonPlace {
    private ArrayList<SkeletonTile> SkeletonArray = new ArrayList<>();
    private int numberOfTiles=0;

    /**
     * Accessor returns the number of the skeletons on the board
     * Postconditions an integer has been returned
     * @return the number of the skeletons on the board
     */
    public int getNumberOfTiles(){return this.numberOfTiles;}

    /**
     * Transformer adds a skeleton tile at the SkeletonPlace
     * Postconditions the Skeleton Tile has been added
     * @param skeletonTile the skeleton tile to add at the SkeletonPlace
     */
    public void addSkeletonTile(SkeletonTile skeletonTile){
        SkeletonArray.add(skeletonTile);
        numberOfTiles++;
    }

    /**
     * Transformer pops a chosen skeleton from the skeleton place
     * Postconditons The chosen skeleton will be removed from the skeleton place and will be returned
     * Preconditions The chosen skeleton must exist on the board
     * @throws TileDoesNotExistException if the tile does not exist
     * @param chosenSkeleton the chosen from the player skeleton to collect
     * @return returns the skeleton
     */
    public SkeletonTile PopFromSkeletonPlace(SkeletonTile chosenSkeleton) throws TileDoesNotExistException{
        for(int i=0; i<SkeletonArray.size(); i++){
            if(SkeletonArray.get(i)==chosenSkeleton){
                SkeletonTile temp = SkeletonArray.get(i);
                SkeletonArray.remove(i);
                numberOfTiles--;
                return temp;
            }
        }
        throw new TileDoesNotExistException();
    }
}