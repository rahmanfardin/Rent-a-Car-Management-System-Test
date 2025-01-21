package BackendCode;

import org.junit.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CarOwnerTest {

    private CarOwner carOwner;

    @Before
    public void setUp() {
        // Set up a fresh CarOwner object before each test
        carOwner = new CarOwner(1000, 1, "12345-6789012-3", "John Doe", "0123456789");
    }

    @After
    public void tearDown() {
        // Clean up any files created during the tests
        File file = new File("CarOwner.ser");
        if (file.exists()) {
            file.delete();
        }
    }
    
    @Test
	public void testSetBalance() {
    	carOwner.setBalance(120);
		assertEquals(120, carOwner.getBalance());
	}

	@Test
	public void testGetBalance() {
		assertEquals(1000, carOwner.getBalance());
	}

    @Test
    public void testAdd() {
        carOwner.Add();
        ArrayList<CarOwner> carOwners = CarOwner.View();
        assertFalse(carOwners.isEmpty());
    }

    @Test
    public void testUpdate() {
        carOwner.Add();
        carOwner.setName("Jane Doe");
        carOwner.Update();

        ArrayList<CarOwner> carOwners = CarOwner.View();
        assertEquals("Jane Doe", carOwners.get(0).Name);
    }

    @Test
    public void testRemove() {
        carOwner.Add();
        carOwner.Remove();

        ArrayList<CarOwner> carOwners = CarOwner.View();
        assertTrue(carOwners.isEmpty());
    }

    @Test
    public void testSearchByName() {
        carOwner.Add();
        ArrayList<CarOwner> carOwners = CarOwner.SearchByName("John Doe");

        assertFalse(carOwners.isEmpty());
    }

    @Test
    public void testSearchByCNIC() {
        carOwner.Add();
        CarOwner foundCarOwner = CarOwner.SearchByCNIC("12345-6789012-3");

        assertNotNull(foundCarOwner);
    }

    @Test
    public void testSearchByID() {
        carOwner.Add();
        CarOwner foundCarOwner = CarOwner.SearchByID(1);

        assertNotNull(foundCarOwner);
    }

    @Test
    public void testGetAllCars() {
        carOwner.Add();
        ArrayList<Car> cars = carOwner.getAllCars();

        assertNotNull(cars);
    }

    @Test
    public void testView() {
        carOwner.Add();
        ArrayList<CarOwner> carOwners = CarOwner.View();

        assertFalse(carOwners.isEmpty());
    }
}
