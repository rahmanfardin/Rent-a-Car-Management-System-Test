package BackendCode;

import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CarTest {

	private Car car;

	@Before
	public void setUp() {
		// Initialize a test car object
		car = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-1234", 100, new CarOwner());
	}

	@After
	public void tearDown() {
		// Clean up the serialized file
		java.io.File file = new java.io.File("Car.ser");
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void testGetID() {
		assertEquals(1, car.getID());
	}

	@Test
	public void testSetID() {
		car.setID(2);
		assertEquals(2, car.getID());
	}

	@Test
	public void testGetMaker() {
		assertEquals("Toyota", car.getMaker());
	}

	@Test
	public void testSetMaker() {
		car.setMaker("Honda");
		assertEquals("Honda", car.getMaker());
	}

	@Test
	public void testGetName() {
		assertEquals("Corolla", car.getName());
	}

	@Test
	public void testSetName() {
		car.setName("Civic");
		assertEquals("Civic", car.getName());
	}

	@Test
	public void testGetColour() {
		assertEquals("White", car.getColour());
	}

	@Test
	public void testSetColour() {
		car.setColour("Red");
		assertEquals("Red", car.getColour());
	}

	@Test
	public void testGetType() {
		assertEquals("Sedan", car.getType());
	}

	@Test
	public void testSetType() {
		car.setType("SUV");
		assertEquals("SUV", car.getType());
	}

	@Test
	public void testGetSeatingCapacity() {
		assertEquals(5, car.getSeatingCapacity());
	}

	@Test
	public void testSetSeatingCapacity() {
		car.setSeatingCapacity(7);
		assertEquals(7, car.getSeatingCapacity());
	}

	@Test
	public void testGetModel() {
		assertEquals("2020", car.getModel());
	}

	@Test
	public void testSetModel() {
		car.setModel("2022");
		assertEquals("2022", car.getModel());
	}

	@Test
	public void testGetCondition() {
		assertEquals("New", car.getCondition());
	}

	@Test
	public void testSetCondition() {
		car.setCondition("Used");
		assertEquals("Used", car.getCondition());
	}

	@Test
	public void testGetRegNo() {
		assertEquals("ABC-1234", car.getRegNo());
	}

	@Test
	public void testSetRegNo() {
		car.setRegNo("XYZ789");
		assertEquals("XYZ789", car.getRegNo());
	}

	@Test
	public void testGetRentPerHour() {
		assertEquals(100, car.getRentPerHour());
	}

	@Test
	public void testSetRentPerHour() {
		car.setRentPerHour(120);
		assertEquals(120, car.getRentPerHour());
	}

	@Test
	public void testSetCarOwner() {
		CarOwner newCarOwner = new CarOwner();
		car.setCarOwner(newCarOwner);
		assertEquals(newCarOwner, car.getCarOwner());
	}

	@Test
	public void testAddCar() {
		car.Add();
		ArrayList<Car> cars = Car.View();
		assertEquals(1, cars.size());
	}

	@Test
	public void testUpdateCar() {
		car.Add();
		car.setName("Camry");
		car.Update();
		Car updatedCar = Car.SearchByID(1);
		assertEquals("Camry", updatedCar.getName());
	}

	@Test
	public void testRemoveCar() {
		car.Add();
		car.Remove();
		Car removedCar = Car.SearchByID(1);
		assertNull(removedCar);
	}

	@Test
	public void testSearchByID() {
		car.Add();
		Car foundCar = Car.SearchByID(1);
		assertEquals("Corolla", foundCar.getName());
	}

	@Test
	public void testSearchByName() {
		car.Add();
		ArrayList<Car> foundCars = Car.SearchByName("Corolla");
		assertEquals(1, foundCars.size());
	}

	@Test
	public void testSearchByRegNo() {
		car.Add();
		Car foundCar = Car.SearchByRegNo("ABC-1234");
		assertEquals("Corolla", foundCar.getName());
	}

	@Test
	public void testNameValidation() {
		assertTrue(Car.isNameValid("Toyota Corolla"));
	}

	@Test
	public void testRegNoValidation() {
		assertTrue(Car.isRegNoValid("ABC-1234"));
	}

	@Test
	public void testIsRented() {
		car.Add();
		assertFalse(car.isRented());
	}
}
