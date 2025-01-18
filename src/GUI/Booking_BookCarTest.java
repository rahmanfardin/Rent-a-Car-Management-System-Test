package GUI;

import BackendCode.Booking;
import BackendCode.Car;
import BackendCode.CarOwner;
import BackendCode.Customer;
import GUI.Booking_BookCar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Booking_BookCarTest {

    private Booking_BookCar bookingGUI;
    private Car car;
    private Customer customer;

    @Before
    public void setUp() {
        // Set up test environment
        bookingGUI = new Booking_BookCar();
        car = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-1234", 100, new CarOwner());
        customer = new Customer(0, 0, "12345-6789012-3", "John Doe", "1234567890");

        car.Add();
        customer.Add();

        // Display the Booking_BookCar GUI directly
        bookingGUI.setSize(300, 200);
        bookingGUI.setVisible(true);
    }


    @After
    public void tearDown() {
        // Clean up after tests
        File bookingFile = new File("Booking.ser");
        File carFile = new File("Car.ser");
        File customerFile = new File("Customer.ser");

        if (bookingFile.exists()) bookingFile.delete();
        if (carFile.exists()) carFile.delete();
        if (customerFile.exists()) customerFile.delete();

        bookingGUI.dispose();
    }

    @Test
    public void testCarBookingProcessValidInput() {
        // Set up valid inputs
        JTextField carIDField = bookingGUI.CarID_TextField;
        JTextField customerIDField = bookingGUI.CustomerID_TextField;
        carIDField.setText("1");
        customerIDField.setText("1");

        JButton bookButton = bookingGUI.Book_Button;
        bookButton.doClick(); // Simulate button click

        ArrayList<Booking> bookings = Booking.View();

        assertEquals(1, bookings.size());
        assertEquals(car.getID(), bookings.get(0).getCar().getID());
        assertEquals(customer.getID(), bookings.get(0).getCustomer().getID());
        assertEquals(0, bookings.get(0).getReturnTime());
    }

    @Test
    public void testCarBookingProcessInvalidCarID() {
        // Set up invalid car ID
        JTextField carIDField = bookingGUI.CarID_TextField;
        JTextField customerIDField = bookingGUI.CustomerID_TextField;
        carIDField.setText("9999"); // Non-existent car ID
        customerIDField.setText("1");

        JButton bookButton = bookingGUI.Book_Button;
        bookButton.doClick();

        ArrayList<Booking> bookings = Booking.View();
        assertEquals(0, bookings.size()); // No bookings should be added
    }

    @Test
    public void testCarBookingProcessInvalidCustomerID() {
        // Set up invalid customer ID
        JTextField carIDField = bookingGUI.CarID_TextField;
        JTextField customerIDField = bookingGUI.CustomerID_TextField;
        carIDField.setText("1");
        customerIDField.setText("9999"); // Non-existent customer ID

        JButton bookButton = bookingGUI.Book_Button;
        bookButton.doClick();

        ArrayList<Booking> bookings = Booking.View();
        assertEquals(0, bookings.size()); // No bookings should be added
    }

    @Test
    public void testCarBookingProcessInvalidInputFormat() {
        // Set up invalid input format
        JTextField carIDField = bookingGUI.CarID_TextField;
        JTextField customerIDField = bookingGUI.CustomerID_TextField;
        carIDField.setText("invalid"); // Non-numeric input
        customerIDField.setText("1");

        JButton bookButton = bookingGUI.Book_Button;
        bookButton.doClick();

        ArrayList<Booking> bookings = Booking.View();
        assertEquals(0, bookings.size()); // No bookings should be added
    }

    @Test
    public void testCancelBookingProcess() {
        // Test cancel button functionality
        JButton cancelButton = bookingGUI.Cancel_Button;
        cancelButton.doClick();

        // Ensure the frame is closed and parent is enabled
        assertFalse(bookingGUI.isDisplayable());
    }
}
