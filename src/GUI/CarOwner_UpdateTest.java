package GUI;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import javax.swing.*;
import BackendCode.CarOwner;

public class CarOwner_UpdateTest {
    private CarOwner_Update carOwnerUpdate;
    private Robot robot;

    @Before
    public void setUp() throws Exception {
        carOwnerUpdate = new CarOwner_Update(); // Initialize the GUI
        carOwnerUpdate.frame.setVisible(true); // Show the GUI for testing
        robot = new Robot();
        robot.setAutoDelay(50); // Delay between key events for smooth execution
        addTestCarOwner(); // Add a test CarOwner to ensure data exists for updating
    }

    @After
    public void tearDown() {
        carOwnerUpdate.frame.dispose(); // Dispose of the GUI after testing
        cleanUpTestData(); // Clean up any remaining test data
    }

    private void addTestCarOwner() {
        // Add a test CarOwner with ID = 1
        CarOwner carOwner = new CarOwner(0, 30, "1234567890123", "Test Owner", "03001234567");
        carOwner.Add();
    }

    private void cleanUpTestData() {
        // Remove the test CarOwner with ID = 1
        CarOwner carOwner = CarOwner.SearchByID(30);
        if (carOwner != null) {
        	System.out.print("remove");
            carOwner.Remove();
        }else System.out.print("notfound");
    }

    @Test
    public void testUpdateButtonWithValidID() {
        // Simulate entering a valid ID
        setTextField(carOwnerUpdate.ID_TextField, "30");

        // Simulate clicking the OK button
        JButton okButton = carOwnerUpdate.OK_Button;
        simulateButtonClick(okButton);

        // Verify that the update window opens (inner class UpdateCarOwner_Inner)
        assertNotNull(CarOwner_Update.carOwner);
        assertEquals(30, CarOwner_Update.carOwner.getID());

        // Simulate updating the details in the inner window
        CarOwner_Update.UpdateCarOwner_Inner updateWindow = carOwnerUpdate.new UpdateCarOwner_Inner();
        updateWindow.setVisible(true);
        setTextField(updateWindow.Name_TextField, "Updated Name");
        setTextField(updateWindow.Contact_TextField, "03111234567");
        simulateButtonClick(updateWindow.Update_Button);

        // Verify that the CarOwner's details were updated
        CarOwner updatedCarOwner = CarOwner.SearchByID(30);
        assertNotNull(updatedCarOwner);
        assertEquals("Updated Name", updatedCarOwner.getName());
        assertEquals("03111234567", updatedCarOwner.getContact_No());
    }

    @Test
    public void testUpdateButtonWithInvalidID() {
        // Simulate entering an invalid ID
        setTextField(carOwnerUpdate.ID_TextField, "invalidID");

        // Simulate clicking the OK button
        JButton okButton = carOwnerUpdate.OK_Button;
        simulateButtonClick(okButton);

        // Verify the validity label displays an error message
        assertEquals("Invalid ID !", carOwnerUpdate.IDValidity_Label.getText());
    }

    @Test
    public void testUpdateButtonWithNonExistentID() {
        // Simulate entering a non-existent ID
        setTextField(carOwnerUpdate.ID_TextField, "999");

        // Simulate clicking the OK button
        JButton okButton = carOwnerUpdate.OK_Button;
        simulateButtonClick(okButton);

        // Verify a JOptionPane with "Required ID is not found!" message is shown
        // Since JOptionPane is modal, validate this manually or with custom dialog mocking
    }

    @Test
    public void testCancelButton() {
        // Simulate clicking the Cancel button
        JButton cancelButton = carOwnerUpdate.Cancel_Button;
        simulateButtonClick(cancelButton);

        // Verify the frame is closed after clicking Cancel
        assertFalse(carOwnerUpdate.frame.isShowing());
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
