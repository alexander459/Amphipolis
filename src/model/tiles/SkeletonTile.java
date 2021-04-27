package model.tiles;
import model.enumerators.*;

/**
 * the SkeletonTile represents a tile of type skeleton
 */
public class SkeletonTile extends Tile{
    private final SkeletonAge Age;
    private final SkeletonBodyPart BodyPart;

    public String toString(){
        return super.toString() + ", Age: " + Age + ", Body Part: " + BodyPart;
    }
    /**
     * Constructor
     * Creates a new skeleton tile with 'Type' Type and 'Part' Part
     * @param Age, Skeleton's age: adult or child
     * @param BodyPart, Skeleton's body part: upper of lower
     */
    public SkeletonTile(SkeletonAge Age, SkeletonBodyPart BodyPart) {
        super(TileType.SKELETON);
        this.Age=Age;
        this.BodyPart=BodyPart;
    }

    /**
     * Accessor returns the Skeleton's type
     * Postconditions The Skeleton's type has been returned
     * @return the Skeleton's type
     */
    public SkeletonAge getAge(){return this.Age;}

    /**
     * Accessor returns the Skeleton's body part
     * Postconditions the Skeleton's body part has been returned
     * @return the Skeleton's body part
     */
    public SkeletonBodyPart getBodyPart(){return this.BodyPart;}
}
