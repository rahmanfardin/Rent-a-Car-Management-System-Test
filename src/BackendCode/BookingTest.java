package BackendCode;

import org.junit.*;
import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class BookingTest {

    private Booking booking;
    private Customer customer;
    private Car car;

    @Before
    public void setUp() {
        customer = new Customer(0, 1, "12345-6789012-3", "John Doe", "1234567890");
        car = new Car(1, "ABC-123", "Toyota", "Corolla", "Available");
        booking = new Booking(1, customer, car, 5);
    }

    @After
    public void tearDown() {
        File bookingFile = new File("Booking.ser");
        File carFile = new File("Car.ser");
        File customerFile = new File("Customer.ser");
        if (bookingFile.exists()) bookingFile.delete();
        if (carFile.exists()) carFile.delete();
        if (customerFile.exists()) customerFile.delete();
    }

    @Test
    public void testAddBooking() {
        booking.Add();
        ArrayList<Booking> bookings = Booking.View();
        assertEquals(1, bookings.size());
        assertEquals(1, bookings.get(0).getID());
    }

    @Test
    public void testUpdateBooking() {
        booking.Add();
        booking.setDays(7);
        booking.Update();
        ArrayList<Booking> bookings = Booking.View();
        assertEquals(7, bookings.get(0).getDays());
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
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getCustomer().getID());
    }

    @Test
    public void testSearchByCarRegNo() {
        booking.Add();
        Booking result = Booking.SearchByCarRegNo("ABC-123");
        assertNotNull(result);
        assertEquals("ABC-123", result.getCar().getRegistrationNumber());
    }

    @Test
    public void testSearchByCarID() {
        booking.Add();
        Booking result = Booking.SearchByCarID(1);
        assertNotNull(result);
        assertEquals(1, result.getCar().getID());
    }

    @Test
    public void testCalculateBill() {
        double expectedBill = booking.CalculateBill();
        assertEquals(5 * car.getRentalRate(), expectedBill, 0.01); // Assuming rental rate is part of Car
    }

    @Test
    public void testViewBookedCars() {
        booking.Add();
        ArrayList<Car> bookedCars = Booking.ViewBookedCars();
        assertEquals(1, bookedCars.size());
        assertEquals("ABC-123", bookedCars.get(0).getRegistrationNumber());
    }

    @Test
    public void testViewUnBookedCars() {
        car.Add();
        booking.Add();
        ArrayList<Car> unBookedCars = Booking.ViewUnBookedCars();
        assertTrue(unBookedCars.isEmpty()); // Assuming all cars are booked in this test
    }
}
