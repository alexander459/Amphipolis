package model.bag;
import model.exceptions.*;
import model.tiles.*;
import java.util.ArrayList;
import java.util.Random;
import model.enumerators.*;

/**
 * Bag class represents the bag with the tiles
 */
public class Bag implements BagADT{
    private final int BAG_SIZE=135;
    private int TilesInBag=0;
    private final ArrayList<Tile> TilesArray = new ArrayList<>();

    /**
     * Constructor
     * Initializes the bag with all the tiles and creates a bag
     * Postconditions The bag has been initialized
     */
    public Bag(){
        //insert the mosaics
        for(int i=0; i<27; i++){
            if(i<9)
                TilesArray.add(new MosaicTile(MosaicColour.GREEN));
            else if(i<18)
                TilesArray.add(new MosaicTile(MosaicColour.RED));
            else
                TilesArray.add(new MosaicTile(MosaicColour.YELLOW));
            TilesInBag++;
        }

        //insert the statues
        for(int i=27; i<51; i++){
            if(i<39)
                TilesArray.add(new CaryatidTile());
            else
                TilesArray.add(new SphinxTile());
            TilesInBag++;
        }

        //insert the landslides
        for(int i=51; i<75; i++){
            TilesArray.add(new LandSlideTile());
            TilesInBag++;
        }

        //insert the skeletons
        for(int i=75; i<105; i++){
            if(i<85)
                TilesArray.add(new SkeletonTile(SkeletonAge.ADULT, SkeletonBodyPart.UPPER));
            else if(i<95)
                TilesArray.add(new SkeletonTile(SkeletonAge.ADULT, SkeletonBodyPart.LOWER));
            else if(i<100)
                TilesArray.add(new SkeletonTile(SkeletonAge.CHILD, SkeletonBodyPart.UPPER));
            else
                TilesArray.add(new SkeletonTile(SkeletonAge.CHILD, SkeletonBodyPart.LOWER));
            TilesInBag++;
        }

        //insert the amphora
        for(int i=105; i<135; i++){
            if(i<110)
                TilesArray.add(new AmphoraTile(AmphoraColour.BLUE));
            else if(i<115)
                TilesArray.add(new AmphoraTile(AmphoraColour.BROWN));
            else if(i<120)
                TilesArray.add(new AmphoraTile(AmphoraColour.RED));
            else if(i<125)
                TilesArray.add(new AmphoraTile(AmphoraColour.GREEN));
            else if(i<130)
                TilesArray.add(new AmphoraTile(AmphoraColour.YELLOW));
            else
                TilesArray.add(new AmphoraTile(AmphoraColour.PURPLE));
            TilesInBag++;
        }
    }

    /**
     * Accessor returns the number of the available tiles in the bag
     * Postconditions the number of the tiles in the bag has been returned
     * returns the number of available tiles which are in the bug
     * @return the number of available tiles which are in the bug
     */
    @Override
    public int getTilesInBag(){return this.TilesInBag;}

    /**
     * Observer returns weather the bag is empty or not
     * Postconditions a boolean haw been returned
     * @return returns true if bag is empty, false if not
     */
    @Override
    public boolean IsEmptyBag(){
        return getTilesInBag()==0;
    }

    /**
     * Transformer Pops a random tile from the bag to use it
     * Preconditions the bag must not be empty
     * Postconditions a random tile has been returned
     * @return returns a random tile from the bag
     * @throws BagEmptyException bag must not be empty
     */
    @Override
    public Tile PopFromBag() throws BagEmptyException{
        if(this.IsEmptyBag())
            throw new BagEmptyException();
        Random random = new Random();
        Tile temp;
        int index = random.nextInt(getTilesInBag());
        temp = TilesArray.get(index);
        TilesArray.remove(index);
        TilesInBag--;
        return temp;
    }

    /**
     * Accessor returns a random amphora
     * @return a random amphora
     * Precondition the bag must contain amphora
     * Postconditions a random amphora has been returned
     * @throws TileDoesNotExistException bag must contain amphora
     */
    @Override
    public AmphoraTile getAmphora() throws TileDoesNotExistException{
        boolean found=false;
        for(int i=0; i<getTilesInBag(); i++){
            if(TilesArray.get(i).getType()==TileType.AMPHORA) {
                found = true;
                break;
            }
        }
        if(!found)
            throw new TileDoesNotExistException();
        AmphoraTile temp;
        Random random=new Random();
        int index;
        while (true){
            index=random.nextInt(getTilesInBag());
            if(TilesArray.get(index).getType()==TileType.AMPHORA){
                temp=(AmphoraTile)TilesArray.get(index);
                TilesArray.remove(index);
                TilesInBag--;
                return temp;
            }
        }
    }

    /**
     * Accessor returns a random mosaic
     * @return a random mosaic
     * Precondition the bag must contain mosaic
     * Postconditions a random mosaic has been returned
     * @throws TileDoesNotExistException bag must contain mosaic
     */
    @Override
    public MosaicTile getMosaic() throws TileDoesNotExistException{
        boolean found=false;
        for(int i=0; i<getTilesInBag(); i++){
            if(TilesArray.get(i).getType()==TileType.MOSAIC) {
                found = true;
                break;
            }
        }
        if(!found)
            throw new TileDoesNotExistException();
        MosaicTile temp;
        Random random=new Random();
        int index;
        while (true){
            index=random.nextInt(getTilesInBag());
            if(TilesArray.get(index).getType()==TileType.MOSAIC){
                temp=(MosaicTile)TilesArray.get(index);
                TilesArray.remove(index);
                TilesInBag--;
                return temp;
            }
        }
    }


    /**
     * Accessor returns a random statue
     * @return a random statue
     * Precondition the bag must contain statue
     * Postconditions a random statue has been returned
     * @throws TileDoesNotExistException bag must contain statue
     */
    @Override
    public StatueTile getStatue() throws TileDoesNotExistException{
        boolean found=false;
        for(int i=0; i<getTilesInBag(); i++){
            if(TilesArray.get(i).getType()==TileType.STATUE) {
                found = true;
                break;
            }
        }
        if(!found)
            throw new TileDoesNotExistException();
        StatueTile temp;
        Random random=new Random();
        int index;
        while (true){
            index=random.nextInt(getTilesInBag());
            if(TilesArray.get(index).getType()==TileType.STATUE){
                temp=(StatueTile)TilesArray.get(index);
                TilesArray.remove(index);
                TilesInBag--;
                return temp;
            }
        }
    }

    /**
     * Accessor returns a random skeleton
     * @return a random skeleton
     * Precondition the bag must contain skeleton
     * Postconditions a random skeleton has been returned
     * @throws TileDoesNotExistException bag must contain skeleton
     */
    @Override
    public SkeletonTile getSkeleton() throws TileDoesNotExistException{
        boolean found = false;
        for (int i = 0; i < getTilesInBag(); i++) {
            if (TilesArray.get(i).getType() == TileType.SKELETON) {
                found = true;
                break;
            }
        }
        if (!found)
            throw new TileDoesNotExistException();
        SkeletonTile temp;
        Random random = new Random();
        int index;
        while (true) {
            index = random.nextInt(getTilesInBag());
            if (TilesArray.get(index).getType() == TileType.SKELETON) {
                temp = (SkeletonTile) TilesArray.get(index);
                TilesArray.remove(index);
                TilesInBag--;
                return temp;
            }
        }
    }
}