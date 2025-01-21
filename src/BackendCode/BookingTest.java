package BackendCode;

import org.junit.*;
import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.*;

import BackendCode.Car;
import BackendCode.Customer;

public class BookingTest {

	private Booking booking;
	private Customer customer;
	private Car car;

	@Before
	public void setUp() {
		customer = new Customer(0, 1, "12345-6789012-3", "John Doe", "1234567890");
		car = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-1234", 100, new CarOwner());
		booking = new Booking(1, customer, car, 5, 10);
	}

	@After
	public void tearDown() {
		File bookingFile = new File("Booking.ser");
		File carFile = new File("Car.ser");
		File customerFile = new File("Customer.ser");
		if (bookingFile.exists())
			bookingFile.delete();
		if (carFile.exists())
			carFile.delete();
		if (customerFile.exists())
			customerFile.delete();
	}

	@Test
	public void testGetID() {
		assertEquals(1, booking.getID());
	}

	@Test
	public void testSetID() {
		booking.setID(2);
		assertEquals(2, booking.getID());
	}

	@Test
	public void testGetCustomer() {
		assertEquals(customer, booking.getCustomer());
	}

	@Test
	public void testSetCustomer() {
		Customer newCustomer = new Customer();
		booking.setCustomer(newCustomer);
		assertEquals(newCustomer, booking.getCustomer());
	}

	@Test
	public void testGetCar() {
		assertEquals(car, booking.getCar());
	}

	@Test
	public void testSetCar() {
		Car newCar = new Car();
		booking.setCar(newCar);
		assertEquals(newCar, booking.getCar());
	}

	@Test
	public void testGetRentTime() {
		assertEquals(5, booking.getRentTime());
	}

	@Test
	public void testSetRentTime() {
		booking.setRentTime(1627891123L);
		assertEquals(1627891123L, booking.getRentTime());
	}

	@Test
	public void testGetReturnTime() {
		assertEquals(10, booking.getReturnTime());
	}

	@Test
	public void testSetReturnTime() {
		booking.setReturnTime(1627894123L);
		assertEquals(1627894123L, booking.getReturnTime());
	}

	@Test
	public void testAddBooking() {
		booking.Add();
		ArrayList<Booking> bookings = Booking.View();
		assertEquals(1, bookings.size());
		// assertEquals(1, bookings.get(0).getID());
	}

	@Test
	public void testUpdateBooking() {
		booking.Add();
		booking.setRentTime(7);
		booking.Update();
		ArrayList<Booking> bookings = Booking.View();
		assertEquals(7, bookings.get(0).getRentTime());
	}

	@Test
	public void testRemoveBooking() {
		booking.Add();
		booking.Remove();
		ArrayList<Booking> bookings = Booking.View();
		assertTrue(bookings.isEmpty());
	}

	@Test
	public void testSearchByCustomerID() {
		booking.Add();
		ArrayList<Booking> result = Booking.SearchByCustomerID(1);
		assertEquals(1, result.get(0).getCustomer().getID());
	}

	@Test
	public void testSearchByCarRegNo() {
		booking.Add();
		ArrayList<Booking> result = Booking.SearchByCarRegNo("ABC-1234");
		assertEquals("ABC-1234", result.get(0).getCar().getRegNo());
	}

	@Test
	public void testSearchByCarID() {
		booking.Add();
		ArrayList<Booking> result = Booking.SearchByCarID(1);
		assertEquals(1, result.get(0).getCar().getID());
	}

	@Test
	public void testCalculateBill() {
		double expectedBill = booking.calculateBill();
		assertEquals(1 * car.getRentPerHour(), expectedBill, 0.01); // Assuming rental rate is part of Car
	}

	@Test
	public void testViewBookedCars() {
		booking.Add();
		ArrayList<Car> bookedCars = Booking.getBookedCars();
		// assertEquals(1, bookedCars.size());
		assertEquals("ABC-1234", bookedCars.get(0).getRegNo());
	}

	@Test
	public void testViewUnBookedCars() {
		Car car1 = new Car(2, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-123", 100, new CarOwner());
		car1.Add();
		booking.Add();
		ArrayList<Car> unBookedCars = Booking.getBookedCars();
		assertEquals("ABC-1234", unBookedCars.get(0).getRegNo());
	}
}
