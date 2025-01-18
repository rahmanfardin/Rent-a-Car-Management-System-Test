package GUI;
import BackendCode.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Car_RemoveTest {

    private Car_Remove carRemove;

    @BeforeEach
    public void setUp() {
        // Initialize the Car_Remove frame before each test
        carRemove = new Car_Remove();
    }

    @Test
    public void testRemoveButtonExists() {
        JButton removeButton = carRemove.Remove_Button;
        assertNotNull(removeButton, "Remove button should not be null");
        assertEquals("Remove", removeButton.getText(), "Remove button should have correct text");
    }

    @Test
    public void testCancelButtonExists() {
        JButton cancelButton = carRemove.Cancel_Button;
        assertNotNull(cancelButton, "Cancel button should not be null");
        assertEquals("Cancel", cancelButton.getText(), "Cancel button should have correct text");
    }

    @Test
    public void testCarIDLabelExists() {
        JLabel carIDLabel = carRemove.CarID_Label;
        assertNotNull(carIDLabel, "Car ID label should not be null");
        assertEquals("Enter Car ID to be removed", carIDLabel.getText(), "Car ID label should have correct text");
    }

    @Test
    public void testCarIDTextFieldExists() {
        JTextField carIDTextField = carRemove.CarID_TextField;
        assertNotNull(carIDTextField, "Car ID text field should not be null");
        assertEquals(240, carIDTextField.getPreferredSize().width, "Car ID text field should have correct width");
        assertEquals(22, carIDTextField.getPreferredSize().height, "Car ID text field should have correct height");
    }

    @Test
    public void testCarIDValidityLabelExists() {
        JLabel carIDValidityLabel = carRemove.CarIDValidity_Label;
        assertNotNull(carIDValidityLabel, "Car ID validity label should not be null");
        assertEquals("", carIDValidityLabel.getText(), "Car ID validity label should initially be empty");
        assertEquals(java.awt.Color.red, carIDValidityLabel.getForeground(), "Car ID validity label should have red color");
    }

    @Test
    public void testRemoveCarFunctionality() {
        // Create a car object and simulate adding it to the system
        Car  testCar = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-1234", 100, new CarOwner());
        testCar.Add(); // Assuming there's an Add method to add the car to the system

        // Set the car ID in the text field
        carRemove.CarID_TextField.setText("1");

        // Simulate clicking the "Remove" button
        carRemove.Remove_Button.doClick();

        // Verify the car was removed
        Car removedCar = Car.SearchByID(1);
        assertNull(removedCar, "Car should be removed from the system");
    }

}
