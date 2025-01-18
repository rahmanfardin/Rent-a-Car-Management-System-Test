package GUI;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import javax.swing.*;
import BackendCode.Customer;

public class Customer_AddTest {
    private Customer_Add customerAdd;

    @Before
    public void setUp() throws Exception {
        customerAdd = new Customer_Add(); // Initialize the GUI
        customerAdd.frame.setVisible(true); // Show the GUI for testing
        cleanUpTestData(); // Ensure no duplicate test data exists before starting
    }

    @After
    public void tearDown() {
        customerAdd.frame.dispose(); // Dispose of the GUI after testing
        cleanUpTestData(); // Clean up any remaining test data
    }

    private void cleanUpTestData() {
        // Remove the test customer with CNIC 1234567890123
        Customer customer = Customer.SearchByCNIC("1234567890124");
        if (customer != null) {
            customer.Remove();
        }
    }

    @Test
    public void testAddButtonWithValidInputs() {
        // Simulate entering valid data into the text fields
        setTextField(customerAdd.CNIC_TextField, "1234567890124");
        setTextField(customerAdd.Name_TextField, "Test Customer");
        setTextField(customerAdd.Contact_TextField, "03001234567");

        // Simulate clicking the Add button
        JButton addButton = customerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the customer with CNIC "1234567890123" has been added
        Customer addedCustomer = Customer.SearchByCNIC("1234567890124");
        assertNotNull("Customer was not added successfully", addedCustomer);
        assertEquals("Test Customer", addedCustomer.getName());
        assertEquals("03001234567", addedCustomer.getContact_No());

        // Verify the frame is closed after a successful addition
        assertFalse("Frame should close after adding customer", customerAdd.frame.isShowing());
    }

    @Test
    public void testAddButtonWithInvalidCNIC() {
        // Simulate entering invalid CNIC
        setTextField(customerAdd.CNIC_TextField, "invalidCNIC");
        setTextField(customerAdd.Name_TextField, "Test Customer");
        setTextField(customerAdd.Contact_TextField, "03001234567");

        // Simulate clicking the Add button
        JButton addButton = customerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the frame is still visible (addition failed)
        assertTrue("Frame should remain open on invalid CNIC", customerAdd.frame.isShowing());
    }

    @Test
    public void testAddButtonWithInvalidName() {
        // Simulate entering valid CNIC and contact, but invalid name
        setTextField(customerAdd.CNIC_TextField, "1234567890124");
        setTextField(customerAdd.Name_TextField, "12345"); // Invalid name
        setTextField(customerAdd.Contact_TextField, "03001234567");

        // Simulate clicking the Add button
        JButton addButton = customerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the frame is still visible (addition failed)
        assertTrue("Frame should remain open on invalid name", customerAdd.frame.isShowing());
    }

    @Test
    public void testAddButtonWithInvalidContact() {
        // Simulate entering valid CNIC and name, but invalid contact
        setTextField(customerAdd.CNIC_TextField, "1234567890124");
        setTextField(customerAdd.Name_TextField, "Test Customer");
        setTextField(customerAdd.Contact_TextField, "invalidPhone"); // Invalid contact

        // Simulate clicking the Add button
        JButton addButton = customerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the frame is still visible (addition failed)
        assertTrue("Frame should remain open on invalid contact", customerAdd.frame.isShowing());
    }

    @Test
    public void testCancelButton() {
        // Simulate clicking the Cancel button
        JButton cancelButton = customerAdd.Cancel_Button;
        simulateButtonClick(cancelButton);

        // Verify the frame is closed after clicking Cancel
        assertFalse("Frame should close after clicking Cancel", customerAdd.frame.isShowing());
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
