import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MoneyTest.
 * Tests Money class.
 *
 * @author  733474
 * @version 18.03.15
 */
public class MoneyTest
{
    /**
     * Default constructor for test class MoneyTest
     */
    public MoneyTest()
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
     * Test that a money can be created.
     */
    @Test
    public void testCreateMoney()
    {
        Money cash = new Money(20);
        assertEquals(20, 20, cash.getAmount());
    }
    
    /**
     * Test that a money can be taking.
     */
    @Test
    public void testTakeMoney()
    {
        Money cash = new Money(20);
        assertEquals(20, 20, cash.getAmount());
        cash.takeMoney(5);
        assertEquals(15, 15, cash.getAmount());
    }
}