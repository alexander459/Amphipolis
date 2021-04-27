package model.board;
import model.tiles.*;
import java.util.ArrayList;
import model.exceptions.*;

/**
 * MosaicPlace class is a singleton class. It represents the MosaicPlace on the board
 */
public class MosaicPlace{
    private final ArrayList<MosaicTile> MosaicArray = new ArrayList<>();
    private int numberOfTiles=0;

    /**
     * Accessor returns the number of the mosaics existing on the board
     * Postconditions an integer has been returned
     * @return the number of the mosaics existing on the board
     */
    public int getNumberOfTiles(){return this.numberOfTiles;}

    /**
     * Transformer adds a mosaic tile at the MosaicPlace
     * Postconditions the Mosaic Tile has been added
     * @param mosaicTile the mosaic tile to add at the MosaicPlace
     */
    public void addMosaicTile(MosaicTile mosaicTile){
        MosaicArray.add(mosaicTile);
        numberOfTiles++;
    }

    /**
     * Transformer pops a chosen mosaic from the mosaic place
     * Postconditons The chosen mosaic will be removed from the mosaic place and will be returned
     * Preconditions The chosen mosaic must exist on the board
     * @throws TileDoesNotExistException if the tile does not exist
     * @param chosenMosaic the chosen from the player mosaic to collect
     * @return returns the mosaic
     */
    public MosaicTile PopFromMosaicPlace(MosaicTile chosenMosaic) throws TileDoesNotExistException{
        for(int i=0; i<MosaicArray.size(); i++){
            if(MosaicArray.get(i)==chosenMosaic){
                MosaicTile temp = MosaicArray.get(i);
                MosaicArray.remove(i);
                numberOfTiles--;
                return temp;
            }
        }
        throw new TileDoesNotExistException();
    }
}
