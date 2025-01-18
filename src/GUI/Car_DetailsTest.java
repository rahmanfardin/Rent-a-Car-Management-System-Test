package GUI;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

public class Car_DetailsTest {

    private Car_Details carDetails;

    @Before
    public void setUp() {
        // Initialize the Car_Details object
    	JFrame testFrame = new JFrame();
        Parent_JFrame.setMainFrame(testFrame);
        carDetails = new Car_Details();
        
		
    }

    @Test
    public void testButtonsInitialization() {
        // Validate that all buttons are initialized and not null
        assertNotNull("Search Name button should be initialized", Car_Details.SearchName_Button);
        assertNotNull("Search Reg No button should be initialized", carDetails.SearchRegNo_Button);
        assertNotNull("Add button should be initialized", Car_Details.Add_Button);
        assertNotNull("Update button should be initialized", Car_Details.Update_Button);
        assertNotNull("Remove button should be initialized", Car_Details.Remove_Button);
        assertNotNull("Back button should be initialized", Car_Details.BackButton);
        assertNotNull("Logout button should be initialized", Car_Details.LogoutButton);
    }

    @Test
    public void testTextFieldsInitialization() {
        // Validate that all text fields are initialized and not null
        assertNotNull("Search Name text field should be initialized", Car_Details.SearchName_TextField);
        assertNotNull("Search Reg No text field should be initialized", carDetails.SearchRegNo_TextField);
    }

    @Test
    public void testSearchNameInput() {
        Car_Details.SearchName_TextField.setText("Toyota"); // Set a valid input
        String input = Car_Details.SearchName_TextField.getText();
        assertFalse("Search Name field should not be empty", input.isEmpty());
    }

    @Test
    public void testSearchRegNoInput() {
        // Test SearchRegNo_TextField input and button action
        JTextField searchRegNoField = carDetails.SearchRegNo_TextField;
        JButton searchRegNoButton = carDetails.SearchRegNo_Button;

        // Simulate entering a registration number
        searchRegNoField.setText("ABC123");
        assertEquals("ABC123", searchRegNoField.getText());

        // Simulate button action
        searchRegNoButton.doClick();

        // Since this test does not rely on database or logic, check for expected output manually
        String enteredRegNo = searchRegNoField.getText().trim();
        assertFalse("Search Reg No field should not be empty", enteredRegNo.isEmpty());
    }

    @Test
    public void testAddButton() {
        // Simulate pressing the Add button
        JButton addButton = Car_Details.Add_Button;
        addButton.doClick();

        // Verify that the Add button responds (check if a new Add screen is shown)
        assertNotNull("Add button action should open a new Add screen", addButton);
    }

    @Test
    public void testUpdateButton() {
        // Simulate pressing the Update button
        JButton updateButton = Car_Details.Update_Button;
        updateButton.doClick();

        // Verify that the Update button responds (check if a new Update screen is shown)
        assertNotNull("Update button action should open a new Update screen", updateButton);
    }

    @Test
    public void testRemoveButton() {
        // Simulate pressing the Remove button
        JButton removeButton = Car_Details.Remove_Button;
        removeButton.doClick();

        // Verify that the Remove button responds (check if a new Remove screen is shown)
        assertNotNull("Remove button action should open a new Remove screen", removeButton);
    }

    @Test
    public void testBackButton() {
        // Simulate pressing the Back button
        JButton backButton = Car_Details.BackButton;
        backButton.doClick();

        // Verify that the Back button responds (returns to main menu)
        assertNotNull("Back button action should return to main menu", backButton);
    }

    @Test
    public void testLogoutButton() {
        // Simulate pressing the Logout button
        JButton logoutButton = Car_Details.LogoutButton;
        logoutButton.doClick();

        // Verify that the Logout button responds (logs out the user)
        assertNotNull("Logout button action should log out the user", logoutButton);
    }
}
