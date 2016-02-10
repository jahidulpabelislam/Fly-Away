import java.util.Timer;
import java.util.TimerTask;

/**
 * Class Timer1 - a timer in an adventure game.
 *
 * This class is part of the "Fly-Away" application. 
 * "Fly-Away" is a very simple, text based adventure game.  
 *
 * A "Timer1" represents a timer in the game.
 * 
 * @author  733474
 * @version 18.03.15
 */
public class Timer1
{
    private Timer timer;
    private boolean timeup;
    private TimerTask task;
    
    /**
     * Create a timer1 with a timer of "seconds".
     * When timer is finished timeup boolean variable
     * is then true.
     * @param seconds The timer's seconds it should last for.
     */
    public Timer1(int seconds) 
    {
        timeup = false;
        timer = new Timer(); //creates the timer
        task = new TimerTask() //creates a new task
        {
            public void run() 
            {
                timeup = true; //turns value to true when timers finished
            }
        };
        
        timer.schedule(task, seconds * 1000); // starts the timer
    }
    
    /**
     * @return timeup value (whether timer has finished or not).
     */
    public boolean getTimeUp()
    {
        return timeup; 
    }
}