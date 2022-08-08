/**
 * The abstract base class for the ships on the board. Classes inheriting this class call the abstract constructor with
 * a size for the ship.
 */
public abstract class Ship {

    private int size;
    private boolean sunk;
    private int hits;

    /**
     * Subclass ships are of different sizes. This constructor assigns the appropriate value to the size field. It also
     * initializes sunk to false and hits to 0.
     * @param size The size of the ship to be created.
     */
    public Ship(int size){
        this.size = size;
        this.sunk = false;
        this.hits = 0;
    }

    /**
     * Accessor for sunk.
     * @return True if the ship has been sunk, false if it has not.
     */
    public boolean getSunk(){
        return sunk;
    }

    /**
     * Increments hits and check to see if hits equals size. If it does it sets sunk to true. Returns value of sunk.
     * @return True if the ship has been sunk, false if it has not.
     */
    public boolean hit() {
        hits += 1;
        if (hits == size)
            sunk = true;
        return sunk;
    }
}
