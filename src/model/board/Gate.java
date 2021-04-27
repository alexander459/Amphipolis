package model.board;
import model.exceptions.*;
import model.tiles.*;
import java.util.ArrayList;

/**
 * Gate class is a singleton class. It represents the gate of the board
 */
public class Gate {
    private boolean GateFull=false;
    private final ArrayList<LandSlideTile> LandSlidesArray = new ArrayList<>();

    /**
     * Transformer adds a landslide at the gate
     * Preconditions the Gate must not be filled
     * Postconditions a landslide has been
     * @param landSlideTile the landslide tile to add at the gate
     */
    public void addLandSlide(LandSlideTile landSlideTile) throws GateClosedException{
        LandSlidesArray.add(landSlideTile);
        if(LandSlidesArray.size()==16){
            GateFull=true;
            throw new GateClosedException();
        }
    }

    /**
     * Observer returns weather the gate is closed from landslides or not
     * @return a boolean that shows if the gate has been closed from the landslides
     */
    public boolean isGateFull(){return GateFull;}


}