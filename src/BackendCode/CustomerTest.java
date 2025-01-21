package BackendCode;

import org.junit.*;
import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() {
        customer = new Customer(0, 0, "12345-6789012-3", "John Doe", "1234567890");
    }

    @After
    public void tearDown() {
        File file = new File("Customer.ser");
        if (file.exists()) {
            file.delete();
        }
    }
    
    @Test
	public void testGetBill() {
		assertEquals(0, customer.getBill());
	}

	@Test
	public void testSetBill() {
		customer.setBill(120);
		assertEquals(120, customer.getBill());
	}

    @Test
    public void testAdd() {
        customer.Add();
        ArrayList<Customer> customers = Customer.View();
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
    }

    @Test
    public void testUpdate() {
        customer.Add();
        customer.setName("Jane Doe");
        customer.Update();
        ArrayList<Customer> customers = Customer.View();
        assertEquals("Jane Doe", customers.get(0).getName());
    }

    @Test
    public void testRemove() {
        customer.Add();
        customer.Remove();
        ArrayList<Customer> customers = Customer.View();
        assertTrue(customers.isEmpty());
    }

    @Test
    public void testSearchByName() {
        customer.Add();
        ArrayList<Customer> result = Customer.SearchByName("John Doe");
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    public void testSearchByCNIC() {
        customer.Add();
        Customer result = Customer.SearchByCNIC("12345-6789012-3");
        assertNotNull(result);
        assertEquals("12345-6789012-3", result.getCNIC());
    }

    @Test
    public void testSearchByID() {
        customer.Add();
        Customer result = Customer.SearchByID(1);
        assertNotNull(result);
        assertEquals(1, result.getID());
    }

    @Test
    public void testView() {
        customer.Add();
        ArrayList<Customer> customers = Customer.View();
        assertEquals(1, customers.size());
        assertEquals("John Doe", customers.get(0).getName());
    }
}
