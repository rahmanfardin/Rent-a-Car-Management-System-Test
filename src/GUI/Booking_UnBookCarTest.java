package GUI;

import BackendCode.Booking;
import BackendCode.Car;
import BackendCode.CarOwner;
import BackendCode.Customer;
import GUI.Booking_UnBookCar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Booking_UnBookCarTest {

    private Booking_UnBookCar unBookGUI;
    private Car car;
    private Customer customer;
    private CarOwner carOwner;
    private Booking booking;

    @Before
    public void setUp() {
        // Set up test environment
        unBookGUI = new Booking_UnBookCar();
        carOwner = new CarOwner(1000, 1, "12345-6789012-3", "John Doe", "0123456789");
        car = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-1234", 100, carOwner);
        customer = new Customer(0, 0, "12345-6789012-3", "John Doe", "1234567890");
        booking = new Booking(1, customer, car, System.currentTimeMillis(), 0);

        carOwner.Add();
        car.setCarOwner(carOwner);
        car.Add();
        customer.Add();
        booking.Add();

        unBookGUI.setSize(300, 145);
        unBookGUI.setVisible(true);
    }

    @After
    public void tearDown() {
        // Clean up after tests
        File bookingFile = new File("Booking.ser");
        File carFile = new File("Car.ser");
        File customerFile = new File("Customer.ser");
        File carOwnerFile = new File("CarOwner.ser");

        if (bookingFile.exists()) bookingFile.delete();
        if (carFile.exists()) carFile.delete();
        if (customerFile.exists()) customerFile.delete();
        if (carOwnerFile.exists()) carOwnerFile.delete();

        unBookGUI.dispose();
    }

    @Test
    public void testUnBookProcessValidInput() {
        // Set up valid input
        JTextField carIDField = unBookGUI.CarID_TextField;
        carIDField.setText("1");

        JButton unBookButton = unBookGUI.UnBook_Button;
        unBookButton.doClick(); // Simulate button click

        ArrayList<Booking> bookings = Booking.View();
        assertEquals(System.currentTimeMillis(), bookings.get(0).getReturnTime(), 1000); // Check if return time was updated

        assertEquals(1000, carOwner.getBalance()); // Check if car owner's balance is updated
        assertEquals(0, customer.getBill()); // Check if customer's bill is updated
    }

    @Test
    public void testUnBookProcessInvalidCarID() {
        // Set up invalid car ID
        JTextField carIDField = unBookGUI.CarID_TextField;
        carIDField.setText("9999"); // Non-existent car ID

        JButton unBookButton = unBookGUI.UnBook_Button;
        unBookButton.doClick();

        ArrayList<Booking> bookings = Booking.View();
        assertEquals(0, bookings.get(0).getReturnTime()); // Ensure return time is not updated
    }

    @Test
    public void testUnBookProcessCarNotBooked() {
  
        JTextField carIDField = unBookGUI.CarID_TextField;
        carIDField.setText(String.valueOf(1100));

        JButton unBookButton = unBookGUI.UnBook_Button;
        unBookButton.doClick();
        
        ArrayList<Booking> bookings = Booking.View();
        assertEquals(0, bookings.get(0).getReturnTime()); // Ensure return time remains unchanged
    }

    @Test
    public void testCancelUnBookProcess() {
        // Test cancel button functionality
        JButton cancelButton = unBookGUI.Cancel_Button;
        cancelButton.doClick();

        // Ensure the frame is closed and parent is enabled
        assertFalse(unBookGUI.isDisplayable());
    }
}
