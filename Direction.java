/**
 *  An enumeration class to represent compass directions
 * 
 * @author  Robert Topp 
 * @version 16.02.2015
 */

public enum Direction
{
    // A value for each direction along with its
    // corresponding user interface string.
    NORTH("north"), SOUTH("south"), EAST("east"), WEST("west");
    
    // The direction string.
    private String directionString;
    
    /**
     * Initialise with the corresponding direction.
     * @param directionString The direction as a string.
     */
    Direction(String directionString)
    {
        this.directionString = directionString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return directionString;
    }
       
}