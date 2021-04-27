package model.board;
import model.tiles.*;
import java.util.ArrayList;
import model.exceptions.*;

public class StatuePlace {
    private final ArrayList<StatueTile> StatueArray = new ArrayList<>();
    int numberOfTiles=0;

    /**
     * Accessor returns the number of the statues on the board
     * Postconditions the number of the statues has been returned
     * @return the number of the statues on the board
     */
    public int getNumberOfTiles(){return  this.numberOfTiles;}

    /**
     * Transformer adds a statue tile at the StatuePlace
     * Postconditions the Statue Tile has been added
     * @param statueTile the statue tile to add at the StatuePlace
     */
    public void addStatueTile(StatueTile statueTile){
        StatueArray.add(statueTile);
        numberOfTiles++;
    }

    /**
     * Transformer pops a chosen statue from the statue place
     * Postconditons The chosen statue will be removed from the statue place and will be returned
     * Preconditions The chosen statue must exist on the board
     * @throws TileDoesNotExistException if the tile does not exist
     * @param chosenStatue the chosen from the player statue to collect
     * @return returns the statue
     */
    public StatueTile PopFromStatuePlace(StatueTile chosenStatue) throws TileDoesNotExistException{
        for(int i=0; i<StatueArray.size(); i++){
            if(StatueArray.get(i)==chosenStatue){
                StatueTile temp = StatueArray.get(i);
                StatueArray.remove(i);
                numberOfTiles--;
                return temp;
            }
        }
        throw new TileDoesNotExistException();
    }
}
