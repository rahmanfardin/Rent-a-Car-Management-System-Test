package GUI;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import javax.swing.*;
import BackendCode.CarOwner;

public class CarOwner_RemoveTest {
    private CarOwner_Remove carOwnerRemove;
    private Robot robot;

    @Before
    public void setUp() throws Exception {
        carOwnerRemove = new CarOwner_Remove(); // Initialize the GUI
        carOwnerRemove.frame.setVisible(true); // Show the GUI for testing
        robot = new Robot();
        robot.setAutoDelay(50); // Delay between key events for smooth execution
        addTestCarOwner(); // Add a test CarOwner to ensure data exists for removal
    }

    @After
    public void tearDown() {
        carOwnerRemove.frame.dispose(); // Dispose of the GUI after testing
        cleanUpTestData(); // Clean up any remaining test data
    }

    private void addTestCarOwner() {
        // Add a test CarOwner with ID = 1
        CarOwner carOwner = new CarOwner(0, 1, "1234567890123", "Test Owner", "03001234567");
        carOwner.Add();
    }

    private void cleanUpTestData() {
        // Remove the test CarOwner with ID = 1
        CarOwner carOwner = CarOwner.SearchByID(1);
        if (carOwner != null) {
            carOwner.Remove();
        }
    }

    @Test
    public void testRemoveButtonWithValidID() {
        // Simulate entering a valid ID
        setTextField(carOwnerRemove.ID_TextField, "1");

        // Simulate clicking the Remove button
        JButton removeButton = carOwnerRemove.Remove_Button;
        simulateButtonClick(removeButton);

        // Verify that the CarOwner with ID = 1 has been removed
        CarOwner removedCarOwner = CarOwner.SearchByID(1);
        assertNull(removedCarOwner);

        // Verify the frame is closed after successful removal
        assertFalse(carOwnerRemove.frame.isShowing());
    }

    @Test
    public void testRemoveButtonWithInvalidID() {
        // Simulate entering an invalid ID
        setTextField(carOwnerRemove.ID_TextField, "invalidID");

        // Simulate clicking the Remove button
        JButton removeButton = carOwnerRemove.Remove_Button;
        simulateButtonClick(removeButton);

        // Verify the frame is still visible (removal failed)
        assertTrue(carOwnerRemove.frame.isShowing());

        // Verify a JOptionPane with the "Enter a valid ID" message is shown
        // Since JOptionPane is modal, you would typically validate this manually or mock JOptionPane in advanced testing
    }

    @Test
    public void testRemoveButtonWithNonExistentID() {
        // Simulate entering a non-existent ID
        setTextField(carOwnerRemove.ID_TextField, "999");

        // Simulate clicking the Remove button
        JButton removeButton = carOwnerRemove.Remove_Button;
        simulateButtonClick(removeButton);

        // Verify the frame is still visible (removal failed)
        assertTrue(carOwnerRemove.frame.isShowing());

        // Verify a JOptionPane with the "This ID does not exist" message is shown
        // Again, validating JOptionPane output might require a custom approach
    }

    @Test
    public void testCancelButton() {
        // Simulate clicking the Cancel button
        JButton cancelButton = carOwnerRemove.Cancel_Button;
        simulateButtonClick(cancelButton);

        // Verify the frame is closed after clicking Cancel
        assertFalse(carOwnerRemove.frame.isShowing());
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

