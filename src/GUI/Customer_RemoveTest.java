package GUI;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import BackendCode.Customer;
import BackendCode.Booking;
import BackendCode.Car;
import BackendCode.CarOwner;

public class Customer_RemoveTest {
    private Customer_Remove customerRemove;
    private Robot robot;

    @Before
    public void setUp() throws Exception {
        customerRemove = new Customer_Remove(); // Initialize the GUI
        customerRemove.frame.setVisible(true); // Show the GUI for testing
        robot = new Robot();
        robot.setAutoDelay(50); // Delay between key events for smooth execution
        addTestCustomerAndBooking(); // Add a test customer and booking for testing
    }

    @After
    public void tearDown() {
        customerRemove.frame.dispose(); // Dispose of the GUI after testing
        cleanUpTestData(); // Clean up any remaining test data
    }

    private void addTestCustomerAndBooking() {
        // Add a test customer with ID = 1
        Customer customer = new Customer(0, 1, "1234567890123", "Test Customer", "03001234567");
        customer.Add();

        
        /*
         *  public Car(int ID, String Maker, String Name, String Colour, String Type, int SeatingCapacity, String Model, String Condition, String RegNo, int RentPerHour, CarOwner carOwner) {
        this.ID = ID;
        this.Maker = Maker;
        this.Name = Name;
        this.Colour = Colour;
        this.Type = Type;
        this.SeatingCapacity = SeatingCapacity;
        this.Model = Model;
        this.Condition = Condition;
        this.RegNo = RegNo;
        this.RentPerHour = RentPerHour;
        this.carOwner = carOwner;
    }*/
        // Add a test car and booking for the customer
        Car car = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2021", "New", "ABC-123", 500, new CarOwner());
        car.Add();
        Booking booking = new Booking(1, customer, car, System.currentTimeMillis(), System.currentTimeMillis() + 86400000); // 1-day booking
        booking.Add();
    }

    private void cleanUpTestData() {
        // Remove the test customer with ID = 1
        Customer customer = Customer.SearchByID(1);
        if (customer != null) {
            customer.Remove();
        }

        // Clean up bookings related to the customer
        ArrayList<Booking> bookings = Booking.View();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getID() == 1) {
                booking.Remove();
            }
        }
    }

    @Test
    public void testRemoveButtonWithValidID() {
        // Simulate entering a valid ID
        setTextField(customerRemove.ID_TextField, "1");

        // Simulate clicking the Remove button
        JButton removeButton = customerRemove.Remove_Button;
        simulateButtonClick(removeButton);

        // Verify that the customer with ID = 1 has been removed
        Customer removedCustomer = Customer.SearchByID(1);
        assertNull("Customer should be removed", removedCustomer);

        // Verify the frame is closed after successful removal
        assertFalse("Frame should close after removing customer", customerRemove.frame.isShowing());
    }

    @Test
    public void testRemoveButtonWithInvalidID() {
        // Simulate entering an invalid ID
        setTextField(customerRemove.ID_TextField, "invalidID");

        // Simulate clicking the Remove button
        JButton removeButton = customerRemove.Remove_Button;
        simulateButtonClick(removeButton);

        // Verify the frame is still visible (removal failed)
        assertTrue("Frame should remain open on invalid ID", customerRemove.frame.isShowing());
    }

    @Test
    public void testRemoveButtonWithNonExistentID() {
        // Simulate entering a non-existent ID
        setTextField(customerRemove.ID_TextField, "999");

        // Simulate clicking the Remove button
        JButton removeButton = customerRemove.Remove_Button;
        simulateButtonClick(removeButton);

        // Verify the frame is still visible (removal failed)
        assertTrue("Frame should remain open on non-existent ID", customerRemove.frame.isShowing());
    }

    @Test
    public void testCancelButton() {
        // Simulate clicking the Cancel button
        JButton cancelButton = customerRemove.Cancel_Button;
        simulateButtonClick(cancelButton);

        // Verify the frame is closed after clicking Cancel
        assertFalse("Frame should close after clicking Cancel", customerRemove.frame.isShowing());
    }

    // Utility methods for testing GUI components
    private void setTextField(JTextField textField, String text) {
        textField.requestFocus();
        textField.setText(text);
    }

    private void simulateButtonClick(JButton button) {
        button.doClick();
    }
}
