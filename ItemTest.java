import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ItemTest.
 * Tests Item class.
 *
 * @author  733474
 * @version 18.03.15
 */
public class ItemTest
{
    /**
     * Default constructor for test class ItemTest
     */
    public ItemTest()
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
     * Test that a item can be added.
     */
    @Test
    public void testAddIten()
    {
        Item phone = new Item("phone", true, 10, Type.OBJECT, 0);
        assertEquals("phone", phone.getName());
        assertEquals(true, phone.getPickable());
        assertEquals(10, phone.getWeight());
        assertEquals(Type.OBJECT, phone.getType());
        assertEquals(0, 0, phone.getCost());
    }
}