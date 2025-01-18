package GUI;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BackendCode.Car;
import BackendCode.CarOwner;

import javax.swing.*;

public class Car_UpdateTest {

    private Car_Update carUpdate;

    @BeforeEach
    public void setUp() {
        // Initialize the Car_Update frame before each test
        carUpdate = new Car_Update();

        // Create a mock Car object and assign it to the carUpdate.car field
        CarOwner carOwner = new CarOwner(5000, 1, "12345-6789012-3", "John Doe", "123456789");
        carUpdate.car = new Car(1, "Toyota", "Corolla", "Red", "Sedan", 5, "2020", "Good", "ABC-1234", 1000, carOwner);
    }

    @Test
    public void testUpdateButtonExists() {
        JButton updateButton = carUpdate.Update_Button;
        assertNotNull(updateButton, "Update button should not be null");
        assertEquals("Update", updateButton.getText(), "Update button should have the correct text");
    }

    @Test
    public void testCancelButtonExists() {
        JButton cancelButton = carUpdate.Cancel_Button;
        assertNotNull(cancelButton, "Cancel button should not be null");
        assertEquals("Cancel", cancelButton.getText(), "Cancel button should have the correct text");
    }

    @Test
    public void testCarIDLabelExists() {
        JLabel carIDLabel = carUpdate.CarID_Label;
        assertNotNull(carIDLabel, "Car ID label should not be null");
        assertEquals("Enter Car ID to be updated", carIDLabel.getText(), "Car ID label should have the correct text");
    }

    @Test
    public void testCarIDTextFieldExists() {
        JTextField carIDTextField = carUpdate.CarID_TextField;
        assertNotNull(carIDTextField, "Car ID text field should not be null");
        assertEquals(240, carIDTextField.getPreferredSize().width, "Car ID text field should have the correct width");
        assertEquals(22, carIDTextField.getPreferredSize().height, "Car ID text field should have the correct height");
    }

    @Test
    public void testCarIDValidityLabelExists() {
        JLabel carIDValidityLabel = carUpdate.CarIDValidity_Label;
        assertNotNull(carIDValidityLabel, "Car ID validity label should not be null");
        assertEquals("", carIDValidityLabel.getText(), "Car ID validity label should initially be empty");
        assertEquals(java.awt.Color.red, carIDValidityLabel.getForeground(), "Car ID validity label should have red color");
    }

    @Test
    public void testMakerTextFieldExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Maker_TextField, "Maker text field should not be null");
    }

    @Test
    public void testNameTextFieldExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Name_TextField, "Name text field should not be null");
    }

    @Test
    public void testColourComboBoxExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Colour_ComboBox, "Colour combo box should not be null");
    }

    @Test
    public void testTypeComboBoxExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Type_ComboBox, "Type combo box should not be null");
    }

    @Test
    public void testModelComboBoxExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Model_ComboBox, "Model combo box should not be null");
    }

    @Test
    public void testSeatingCapacitySpinnerExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.SeatingCapacity_Spinner, "Seating capacity spinner should not be null");
    }

    @Test
    public void testConditionComboBoxExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Condition_ComboBox, "Condition combo box should not be null");
    }

    @Test
    public void testInnerUpdateButtonExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Update_Button, "Inner update button should not be null");
    }

    @Test
    public void testInnerCancelButtonExists() {
        Car_Update.Car_UpdateInner innerFrame = carUpdate.new Car_UpdateInner();
        assertNotNull(innerFrame.Cancel_Button, "Inner cancel button should not be null");
    }
}
