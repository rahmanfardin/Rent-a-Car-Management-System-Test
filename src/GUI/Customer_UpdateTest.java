package GUI;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import javax.swing.*;
import BackendCode.Customer;

public class Customer_UpdateTest {
    private Customer_Update customerUpdate;
    private Robot robot;

    @Before
    public void setUp() throws Exception {
        customerUpdate = new Customer_Update(); // Initialize the GUI
        customerUpdate.frame.setVisible(true); // Show the GUI for testing
        robot = new Robot();
        robot.setAutoDelay(50); // Delay between key events for smooth execution
        addTestCustomer(); // Add a test customer for testing
    }

    @After
    public void tearDown() {
        customerUpdate.frame.dispose(); // Dispose of the GUI after testing
        cleanUpTestData(); // Clean up any remaining test data
    }

    private void addTestCustomer() {
        // Add a test customer with ID = 1
        Customer customer = new Customer(100, 1, "1234567890123", "Test Customer", "03001234567");
        customer.Add();
    }

    private void cleanUpTestData() {
        // Remove the test customer with ID = 1
        Customer customer = Customer.SearchByID(1);
        if (customer != null) {
            customer.Remove();
        }
    }

    @Test
    public void testUpdateButtonWithValidID() {
        // Simulate entering a valid ID
        setTextField(customerUpdate.ID_TextField, "1");

        // Simulate clicking the OK button
        JButton okButton = customerUpdate.OK_Button;
        simulateButtonClick(okButton);

        // Verify that the Update window opens (inner class UpdateCustomer_Inner)
        assertNotNull("Customer object should be set", Customer_Update.customer);
        assertEquals(1, Customer_Update.customer.getID());

        // Simulate updating the details in the inner window
        Customer_Update.UpdateCustomer_Inner updateWindow = customerUpdate.new UpdateCustomer_Inner();
        updateWindow.setVisible(true);

        setTextField(updateWindow.Name_TextField, "Updated Customer");
        setTextField(updateWindow.Contact_TextField, "03111234567");
        simulateButtonClick(updateWindow.Update_Button);

        // Verify that the customer's details were updated
        Customer updatedCustomer = Customer.SearchByID(1);
        assertNotNull("Customer should exist after update", updatedCustomer);
        assertEquals("Updated Customer", updatedCustomer.getName());
        assertEquals("03111234567", updatedCustomer.getContact_No());
    }

    @Test
    public void testUpdateButtonWithInvalidID() {
        // Simulate entering an invalid ID
        setTextField(customerUpdate.ID_TextField, "invalidID");

        // Simulate clicking the OK button
        JButton okButton = customerUpdate.OK_Button;
        simulateButtonClick(okButton);

        // Verify the ID validity label displays an error message
        assertEquals("Invalid ID !", customerUpdate.IDValidity_Label.getText());
    }

    @Test
    public void testUpdateButtonWithNonExistentID() {
        // Simulate entering a non-existent ID
        setTextField(customerUpdate.ID_TextField, "999");

        // Simulate clicking the OK button
        JButton okButton = customerUpdate.OK_Button;
        simulateButtonClick(okButton);

        // Verify that a JOptionPane with "Required ID is not found!" is shown
        // Manual verification or mocking JOptionPane is required for further validation
    }

    @Test
    public void testCancelButton() {
        // Simulate clicking the Cancel button
        JButton cancelButton = customerUpdate.Cancel_Button;
        simulateButtonClick(cancelButton);

        // Verify the frame is closed after clicking Cancel
        assertFalse("Frame should close after clicking Cancel", customerUpdate.frame.isShowing());
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
