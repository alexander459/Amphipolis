package model.player;
import model.tiles.*;
import java.util.ArrayList;
import model.enumerators.*;

/**
 * this class represents the tile collection of a player
 */
public class PlayersCollection implements PlayersCollectionADT{
    private final ArrayList<MosaicTile> MosaicArray = new ArrayList<>();
    private final ArrayList<AmphoraTile> AmphoraArray = new ArrayList<>();
    private final ArrayList<StatueTile> StatueArray = new ArrayList<>();
    private final ArrayList<SkeletonTile> AdultSkeletonArray = new ArrayList<>();
    private final ArrayList<SkeletonTile> ChildSkeletonArray = new ArrayList<>();
    private int numberOfSphinx=0;
    private int numberOfCaryatid=0;

    /**
     * Accessor returns the number of the collected sphinx
     * Postconditions an integer has been returned
     * @return the number of the collected sphinx
     */
    public int getNumberOfSphinx(){return this.numberOfSphinx;}

    /**
     * Accessor returns the number of the collected caryatid
     * Postconditions an integer has been returned
     * @return the number of the collected caryatid
     */
    public int getNumberOfCaryatid(){return this.numberOfCaryatid;}

    /**
     * Transformer puts a mosaic tile at players MosaicArray
     * Postcondition a mosaic tile has been put at the players MosaicArray
     * @param mosaicTile the mosaic tile to be added at the players MosaicArray
     */
    @Override
    public void addMosaic(MosaicTile mosaicTile){
        MosaicArray.add(mosaicTile);
    }

    /**
     * Transformer puts an amphora tile at players AmphoraArray
     * Postcondition a amphora tile has been put at the players AmphoraArray
     * @param amphoraTile the amphora tile to be added at the players AmphoraArray
     */
    @Override
    public void addAmphora(AmphoraTile amphoraTile){
        AmphoraArray.add(amphoraTile);
    }

    /**
     * Transformer puts a statue tile at players StatueArray
     * Postcondition a statue tile has been put at the players StatueArray
     * @param statueTile the statue tile to be added at the players StatueArray
     */
    @Override
    public void addStatue(StatueTile statueTile){
        if(statueTile.getStatueType()==StatueType.CARYATID)
            numberOfCaryatid++;
        else
            numberOfSphinx++;
        StatueArray.add(statueTile);
    }

    /**
     * Transformer puts a skeleton tile at players SkeletonArray
     * Postcondition a skeleton tile has been put at the players SkeletonArray
     * @param skeletonTile the skeleton tile to be added at the players SkeletonArray
     */
    @Override
    public void addSkeleton(SkeletonTile skeletonTile){
        if(skeletonTile.getAge()==SkeletonAge.ADULT)
            AdultSkeletonArray.add(skeletonTile);
        else
            ChildSkeletonArray.add(skeletonTile);
    }

    /**
     * Accessor calculates and returns the total points collected from the mosaics tiles
     * Postconditions the points have been calculated and returned
     * @return the total points collected from the mosaics tiles
     */
    @Override
    public int getMosaicPoints(){
        int red=0;
        int green=0;
        int yellow=0;
        int points=0;
        int temp;
        for(int i=0; i<MosaicArray.size(); i++){
            switch(MosaicArray.get(i).getColour()){
                case RED:
                    red++;
                    break;
                case GREEN:
                    green++;
                    break;
                case YELLOW:
                    yellow++;
                    break;
            }
        }
        points=points+red/4;
        red=red%4;

        points=points+yellow/4;
        yellow=yellow%4;

        points=points+green/4;
        green=green%4;

        points=points*4;

        temp=red+yellow+green;
        temp=temp/4;
        temp=temp*2;

        points=points+temp;
        return points;
    }

    /**
     * Accessor calculates and returns the total points collected from the amphora tiles
     * Postconditions the points have been calculated and returned
     * @return the total points collected from the amphora tiles
     */
    @Override
    public int getAmphoraPoints(){
        int points=0;
        boolean found;
        ArrayList<AmphoraTile> temp = new ArrayList<>();

        while(AmphoraArray.size()!=0) {
            for (int i = 0; i < AmphoraArray.size(); i++) {
                found = false;
                for (int j = 0; j < temp.size(); j++) {
                    if (AmphoraArray.get(i).getColour() == temp.get(j).getColour()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    temp.add(AmphoraArray.get(i));
                    AmphoraArray.remove(i);
                    i--;
                }
            }
            switch (temp.size()){
                case 6:
                    points=points+6;
                    break;
                case 5:
                    points=points+4;
                    break;
                case 4:
                    points=points+2;
                    break;
                case 3:
                    points=points+1;
                    break;
            }
            temp=new ArrayList<>();
        }
        return points;
    }

    /**
     * Transformer sets the number of caryatid and the number of the sphinx
     * Postconditions the number of the collected statued has been set
     */
    @Override
    public void setNumberOfStatues(){
        for(int i=0; i<StatueArray.size(); i++){
            if(StatueArray.get(i).getStatueType()==StatueType.CARYATID)
                numberOfCaryatid++;
            else
                numberOfSphinx++;
        }
    }

    /**
     * Accessor calculates and returns the total points collected from the skeleton tiles
     * Postconditions the points have been calculated and returned
     * @return the total points collected from the skeleton tiles
     */
    @Override
    public int getSkeletonPoints(){
        int points=0;
        int upper=0;
        int lower=0;
        int pairs;
        int families;
        int adultSkeletons=0;
        int childSkeletons=0;
        for(int i=0; i<AdultSkeletonArray.size(); i++){
            if(AdultSkeletonArray.get(i).getBodyPart()==SkeletonBodyPart.LOWER)
                lower++;
            else
                upper++;
        }
        if(lower<upper)
            adultSkeletons=lower;
        else
            adultSkeletons=upper;
        pairs=adultSkeletons/2;
        //adultSkeletons=adultSkeletons%2;
        lower=0;
        upper=0;
        for(int i=0; i<ChildSkeletonArray.size(); i++){
            if(ChildSkeletonArray.get(i).getBodyPart()==SkeletonBodyPart.LOWER)
                lower++;
            else
                upper++;
        }
        if(lower<upper)
            childSkeletons=lower;
        else
            childSkeletons=upper;


        if(childSkeletons<pairs) {
            families = childSkeletons;
            childSkeletons=0;
        }else{
            families = pairs;
            adultSkeletons=adultSkeletons%2;
            childSkeletons=childSkeletons-families;
        }
        points=points+families*6;
        points=points+childSkeletons+adultSkeletons;
        return points;
    }
}