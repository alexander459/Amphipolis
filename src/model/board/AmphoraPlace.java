package model.board;
import model.tiles.*;
import java.util.ArrayList;
import model.exceptions.*;

/**
 * AmphoraPlace class is a singleton class. It represents the AmphoraPlace on the board
 */
public class AmphoraPlace {
    private final ArrayList<AmphoraTile> AmphoraArray = new ArrayList<>();
    private int numberOfTiles=0;

    /**
     * Accessor returns the number of the amphora tiles on the amphora place
     * Postconditions an integer has been returned
     * @return the number of the amphora tiles on the amphora place
     */
    public int getNumberOfTiles(){return this.numberOfTiles;}

    /**
     * Transformer adds an amphora tile at the AmphoraPlace
     * Postconditions the Amphora Tile has been added
     * @param amphoraTile the amphora tile to add at the AmphoraPlace
     */
    public void addAmphoraTile(AmphoraTile amphoraTile){
        AmphoraArray.add(amphoraTile);
        numberOfTiles++;
    }

    /**
     * Transformer pops a chosen amphora from the amphora place
     * Postconditons The chosen amphora will be removed from the amphora place and will be returned
     * Preconditions The chosen amphora must exist on the board
     * @throws TileDoesNotExistException if the tile does not exist
     * @param chosenAmphora the chosen from the player amphora to collect
     * @return returns the amphora
     */
    public AmphoraTile PopFromAmphoraPlace(AmphoraTile chosenAmphora) throws TileDoesNotExistException{
        for(int i=0; i<AmphoraArray.size(); i++){
            if(AmphoraArray.get(i)==chosenAmphora){
                AmphoraTile temp = AmphoraArray.get(i);
                AmphoraArray.remove(i);
                numberOfTiles--;
                return temp;
            }
        }
        throw new TileDoesNotExistException();
    }
}
