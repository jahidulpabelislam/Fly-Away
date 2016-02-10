/**
 * Class Money - a amount of money a player has in a Game..
 *
 * This class is part of the "Fly-Away" application. 
 * "Fly-Away" is a very simple, text based adventure game.  
 *
 * A "Money" represents amount of money a player has. 
 * 
 * @author  733474
 * @version 18.03.15
 */
public class Money
{
    private double amount;
     
    /**
     * Create a "amount" of money.
     * @param amount the amount of money it starts with.
     */
    public Money(double amount)
    {
        this.amount = amount;
    }
    
    /**
     * @return The amount.
     */
    public double getAmount()
    {
        return amount;
    }
    
    /**
     * take money from current money
     * @param amount the amount to take from current amount
     */
    public void takeMoney(double amount)
    {
        this.amount -= amount;
    }
}