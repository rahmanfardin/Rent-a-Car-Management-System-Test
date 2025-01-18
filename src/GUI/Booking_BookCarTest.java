package GUI;

import BackendCode.Booking;
import BackendCode.Car;
import BackendCode.CarOwner;
import BackendCode.Customer;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Booking_BookCarTest {

    private Booking_BookCar bookingBookCar;
    private JTextField carIDTextField;
    private JTextField customerIDTextField;
    private JButton bookButton;
    private JButton cancelButton;
    private Car testCar;
    private Customer testCustomer;

    @Before
    public void setUp() {
        bookingBookCar = new Booking_BookCar();
        carIDTextField = bookingBookCar.CarID_TextField;
        customerIDTextField = bookingBookCar.CustomerID_TextField;
        bookButton = bookingBookCar.Book_Button;
        cancelButton = bookingBookCar.Cancel_Button;
        
        testCustomer = new Customer(0, 1, "12345-6789012-3", "John Doe", "1234567890");
        
        testCar = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-1234", 100, new CarOwner());
    }

    @After
    public void tearDown() {
        bookingBookCar.dispose();
        Car.Remove();
        Customer.Remove(); 
    }

    @Test
    public void testBookButtonValidInputs() {
        carIDTextField.setText("1");
        customerIDTextField.setText("1");

        // Simulate button click
        bookButton.doClick();

        // Verify expected behavior
        Booking booking = Booking.getBookingByCarId(testCar.getID()); // Assuming there's a method to get booking by car ID
        assertNotNull(booking);
        assertEquals(testCustomer.getID(), booking.getCustomer().getID());
        assertEquals(testCar.getID(), booking.getCar().getID());
    }

    @Test
    public void testBookButtonInvalidCarID() {
        carIDTextField.setText("invalid");
        customerIDTextField.setText("1");

        // Simulate button click
        bookButton.doClick();

        // Verify expected behavior
        assertNull(Booking.getBookingByCarID(testCar.getID())); // No booking should be created
    }

    @Test
    public void testBookButtonInvalidCustomerID() {
        carIDTextField.setText("1");
        customerIDTextField.setText("invalid");

        // Simulate button click
        bookButton.doClick();

        // Verify expected behavior
        assertNull(Booking.getBookingByCarId(testCar.getId())); // No booking should be created
    }

    @Test
    public void testCancelButton() {
        // Simulate button click
        cancelButton.doClick();

        // Add assertions here to verify the expected behavior
        // For example, you could check if the main frame is enabled
        assertTrue(Parent_JFrame.getMainFrame().isEnabled());
    }
}
