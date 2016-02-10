import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.Thread;

/**
 * The test class Timer1Test.
 * Tests Timer1 class.
 *
 * @author  733474
 * @version 18.03.15
 */
public class Timer1Test
{
    /**
     * Default constructor for test class Timer1Test
     */
    public Timer1Test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * Test that a timer1 can be created.
     */
    @Test
    public void testCreateTimer1()
    {
        Timer1 timer = new Timer1(5);
        assertEquals(false, timer.getTimeUp());
    }
}