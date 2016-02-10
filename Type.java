/**
 *  An enumeration class to represent item types
 * 
 * @author  733474
 * @version 18.03.15
 */

public enum Type
{
    // A value for each type along with its
    // corresponding user interface string.
    OBJECT("object"), FOOD("food"), DRINK("drink"), PRODUCT("product");
    
    // The type string.
    private String typeString;
    
    /**
     * Initialise with the corresponding type.
     * @param typeString The type as a string.
     */
    Type(String typeString)
    {
        this.typeString = typeString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return typeString;
    }
       
}