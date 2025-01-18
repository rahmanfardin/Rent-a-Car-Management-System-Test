package GUI;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import javax.swing.*;
import BackendCode.CarOwner;

public class CarOwner_AddTest {
    private CarOwner_Add carOwnerAdd;

    @Before
    public void setUp() throws Exception {
        carOwnerAdd = new CarOwner_Add(); // Initialize the GUI
        carOwnerAdd.frame.setVisible(true); 
    }

    @After
    public void tearDown() {
        carOwnerAdd.frame.dispose(); // Dispose of the GUI after testing
        cleanUpTestData(); // Ensure cleanup after each test
    }

    private void cleanUpTestData() {
        // Remove test CNICs that may have been added during testing
        CarOwner carOwner = CarOwner.SearchByCNIC("1234567890124");
        if (carOwner != null) {
            carOwner.Remove();
        }
    }

    @Test
    public void testAddButtonWithValidInputs() {
        // Simulate entering valid data into the text fields
        setTextField(carOwnerAdd.CNIC_TextField, "1234567890124");
        setTextField(carOwnerAdd.Name_TextField, "John Doe");
        setTextField(carOwnerAdd.Contact_TextField, "03001234567");

        // Simulate clicking the Add button
        JButton addButton = carOwnerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the frame was disposed after a successful addition
        assertFalse(carOwnerAdd.frame.isShowing());

        // Ensure the CNIC was added correctly
        CarOwner addedCarOwner = CarOwner.SearchByCNIC("1234567890124");
        assertNotNull(addedCarOwner);
        assertEquals("John Doe", addedCarOwner.getName());
    }

    @Test
    public void testAddButtonWithInvalidCNIC() {
        // Simulate entering invalid CNIC
        setTextField(carOwnerAdd.CNIC_TextField, "invalidCNIC");
        setTextField(carOwnerAdd.Name_TextField, "John Doe");
        setTextField(carOwnerAdd.Contact_TextField, "03001234567");

        // Simulate clicking the Add button
        JButton addButton = carOwnerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the frame is still visible (addition failed)
        assertTrue(carOwnerAdd.frame.isShowing());

        // Verify the validity label has an error message
        assertEquals("Invalid CNIC", carOwnerAdd.CNICValidity_Label.getText());
    }

    @Test
    public void testAddButtonWithInvalidPhone() {
        // Simulate entering valid CNIC and name, but invalid phone number
        setTextField(carOwnerAdd.CNIC_TextField, "1234567890124");
        setTextField(carOwnerAdd.Name_TextField, "John Doe");
        setTextField(carOwnerAdd.Contact_TextField, "invalidPhone");

        // Simulate clicking the Add button
        JButton addButton = carOwnerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the frame is still visible (addition failed)
        assertTrue(carOwnerAdd.frame.isShowing());

        // Verify the error message on the contact validity label
        assertEquals("Invalid contact no. !", carOwnerAdd.contactValidity_Label.getText());
    }

    @Test
    public void testAddButtonWithInvalidName() {
        // Simulate entering valid CNIC and phone number, but invalid name
        setTextField(carOwnerAdd.CNIC_TextField, "1234567890124");
        setTextField(carOwnerAdd.Name_TextField, "12345"); // Invalid name
        setTextField(carOwnerAdd.Contact_TextField, "03001234567");

        // Simulate clicking the Add button
        JButton addButton = carOwnerAdd.Add_Button;
        simulateButtonClick(addButton);

        // Verify that the frame is still visible (addition failed)
        assertTrue(carOwnerAdd.frame.isShowing());

        // Verify the error message on the name validity label
        assertEquals("Invalid Name !", carOwnerAdd.NameValidity_Label.getText());
    }

    @Test
    public void testCancelButton() {
        // Simulate clicking the Cancel button
        JButton cancelButton = carOwnerAdd.Cancel_Button;
        simulateButtonClick(cancelButton);

        // Verify that the frame was disposed after clicking Cancel
        assertFalse(carOwnerAdd.frame.isShowing());
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
