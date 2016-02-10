import java.util.ArrayList;
/**
 *  A class to represent an adventurer playing the exciting
 *  game of "Fly-Away". A player has a location (of class Room)
 * and is facing a particular direction with objects in backpack.
 * 
 * @author Robert Topp, 733474
 * @version 18.03.15
 */

public class Player
{
    private Room here;
    private Direction facing;
    private ArrayList<Item> backpack;
  
    /**
     * Construct a player with "null" location
     * facing east with no items in backpack
     */
    public Player()
    {
        here = null;
        facing = Direction.EAST;
        backpack = new ArrayList<Item>();
    }
    
    /**
     * @return the direction the player is facing
     */
    public Direction getDirection()
    {
        return facing;
    }
    
    /**
     * @return the room the player is currently in
     */
    public Room getRoom()
    {
        return here;
    }
    
    /**
     * make the player turn left
     */
    public void turnLeft()
    {
        if(facing == Direction.NORTH)
        {facing = Direction.WEST;} 
        else if(facing == Direction.WEST)
        {facing = Direction.SOUTH;}
        else if(facing == Direction.SOUTH)
        {facing = Direction.EAST;}
        else
        {facing = Direction.NORTH;}
    }
    
    /**
     * make the player turn right
     */
    public void turnRight()
    {
        if(facing == Direction.NORTH)
        {facing = Direction.EAST;} 
        else if(facing == Direction.WEST)
        {facing = Direction.NORTH;}
        else if(facing == Direction.SOUTH)
        {facing = Direction.WEST;}
        else
        {facing = Direction.SOUTH;}
    }
    
    /**
     * set the players current location
     * @param r the room to set the players current location
     */
    public void setRoom(Room r)
    {
        here = r;
    }
    
    /**
     * set the direction the player is facing
     * @param d the direction to set the direction the player is facing
     */
    public void setDirection(Direction d)
    {
        facing = d;
    }
    
    /**
     * @return the number of items in players backpack
     */
    public int numberOfItemsInBackPack()
    {
        return backpack.size();
    }
    
    /**
     * add item to players backpack
     * @param item the item to add to players backpack
     */
    public void putInBackPack(Item item)
    {
        backpack.add(item);
    }
    
    /**
     * remove item from players backpack
     * @param i index of item to remove from players backpack
     */
    public void removeFromBackPack(int i)
    {
        if(i >= 0 & i < backpack.size())
        {
            backpack.remove(i);
        }
    }
    
    /**
     * @param search the item's name of item looking for
     * @return the item if found otherwise return null
     */
    public Item findItem(String search)
    {
        for(Item item : backpack)
        {
          if (item.getName().equals(search))
          {
            return item;
          }
        }
        return null;
    } 
    
    /**
     * Calculate the total weight of all items in players backpack.
     * @return the total weight of items in players backpack.
     */
    public int getBackPackWeight()
    {
        int total = 0;
        for(Item item : backpack)
        {
          total += item.getWeight();
        }
        return total;
    } 
    
    /**
     * Return a string describing the players backpack, for example
     * "Backpack: passport".
     * @return Details of the players backpack.
     */
    public String getBackPackItems()
    {        
        String returnString = "Items carrying:";
        for(Item item : backpack) 
        {
          returnString += " " + item.getName();
        }
        return returnString;
    }
    
    /**
     * @param i the item looking for
     * @return the index of a item.
     */
    public int getIndex(Item i)
    {
        return backpack.indexOf(i); 
    }
}