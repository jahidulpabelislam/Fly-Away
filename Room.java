import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Fly-Away" application. 
 * "Fly-Away" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room. Each room can have a "Item" or more.
 * 
 * @author  Michael Kölling and David J. Barnes, 733474
 * @version 2015.03.18
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;           // stores items in room.

    /**
     * Create a room described "description". Initially, it has
     * no exits and no items in room. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west.
     *     Items in Room: microwave
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getRoomItems();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) 
        {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
     /**
      * @return the number of items in room
      */
    public int numberOfThingsInRoom()
    {
        return items.size();
    }
     
    /**
     * add item to room
     * @param item the item to add to room
     */
    public void putInRoom(Item item)
    {
        items.add(item);
    }
     
    /**
     * remove item from room
     * @param i the index of item to remove
     */
    public void removeFromRoom(int i)
    {
        if(i >= 0 & i < items.size())
        {
            items.remove(i);
        }
    }
    
    /**
     * @param search the item's name of item looking for
     * @return the item if found otherwise return null
     */
    public Item findItem(String search)
    {
        for(Item item : items)
        {
            if (item.getName().equals(search))
            {
              return item;
            }
        }
        return null;
    }  
    
    /**
     * Return a string listing all the room's items, for example
     * "Items in room: passport".
     * @return string of all the room's items.
     */
    private String getRoomItems()
    {
        String returnString = "Items in room:";
        for(Item item : items) 
        {
            returnString += " " + item.getName();
        }
        return returnString;
    }
    
    /**
     * @param item the item looking index of
     * @return the index of a item.
     */
    public int getIndex(Item item)
    {
        return items.indexOf(item); 
    }
}